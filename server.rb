require 'socket'
require 'singleton'

class Fixnum
  def to_twp
    TWP3::Types::ShortInteger.new(self).to_s
  end
end

module TWP3
  MAGIC_BYTES    = "TWP3\n"
  END_OF_CONTENT = 0
 
  module Types
    class ShortInteger
      CODE = 13
      
      def initialize(value)
        raise Exception unless (-128..127).include?(value)
        @value = value
      end
      
      def to_s
        CODE.chr + @value.chr
      end
    end
    
    class ShortString
      RANGE = 17..126
      
      def initialize(value)
        raise Exception unless (0..109).include?(value.length)
        @value = value
      end
      
      def to_s
        (17 + @value.length).chr + @value 
      end
    end    
  end
  
  class Connection
    include Singleton
    
    attr_writer :protocol
    
    def initialize
      @connected = false
      at_exit { disconnect }
    end
    
    def self.setup(host, port = 80)
      instance.setup(host, port)
    end
    
    def self.disconnect
      instance.disconnect
    end
    
    def self.connected?
      instance.connected?
    end
    
    def setup(host, port = 80)
      @host, @port = host, port
    end
    
    def connected?
      @connected
    end
    
    def connect
      begin
        @socket = TCPSocket.new(@host, @port)
        @socket.write MAGIC_BYTES
        @socket.write @protocol.to_twp
        @connected = true
      rescue SocketError
        @socket.close if @socket && !@socket.closed?
        STDERR.puts "Connection Error"
        exit 1
      end
    end
    
    def disconnect
      @socket.close if @socket && !@socket.closed?
      @connected = false
    end
    
    def read(*args)
      @socket.read(*args)
    end
    
    def write(*args)
      @socket.write(*args)
    end
  end
  
  module Messages
    class Request
      attr_reader :parameters
      
      def initialize(connection)
        @connection = connection
        @parameters = []
      end
    
      def message=(id)
        # offset 4 = alternative 0 # id must be 0 to 7        
        @message = (4 + id).chr
      end
    
      def submit
        @connection.write @message
        @parameters.each do |param|
          @connection.write param
        end        
        @connection.write END_OF_CONTENT.chr
      end
    end
  
    class Response
      attr_reader :message, :parameters
      
      def initialize(connection)
        @connection = connection
        @parameters = []
      end

      def receive
        @message = @connection.read(1).ord - 4
        loop do
          msg = @connection.read(1).ord
          case msg
            when END_OF_CONTENT
              then return
            when Types::ShortInteger::CODE
              then @parameters << @connection.read(1).ord
            when Types::ShortString::RANGE
              then @parameters << @connection.read(msg - 17)
          end
        end
      end
    end
  end
  
  # new stuff
  
  class Specification
    attr_reader :protocols
    
    def initialize(&block)
      @protocols = []
      instance_exec(&block) if block_given?
    end
    
    def method_missing(name, *args, &block)
      @protocols.find {|p| p.name == name.to_s} || super
    end
  end
  
  class Protocol
    attr_reader :id, :name, :messages
    
    def initialize(name, id, &block)
      @name, @id = name, id
      @messages  = []
      instance_exec(&block) if block_given?
    end

    def method_missing(name, *args, &block)
      message = @messages.find {|p| p.name == name.to_s}
      message ? message.submit(*args, &block) : super
    end      
  end
  
  class Message
    attr_reader :id, :name, :parameters
    attr_writer :protocol
    
    def initialize(name, id, protocol, &block)
      @name, @id, @protocol = name, id, protocol
      @parameters = []
      instance_exec(&block) if block_given?
    end
    
    def submit(*args, &block)
      # don't open a new connection for every message
      #
      # check wether con is already connected?
      # if no: connect
      # if yes: check protocol
      #   if matches: do nothing
      #   if not: disconnect and reconnect with new protocol
      #
      # need to close socket when program ends or class is killed
      #
      con = Connection.instance
      if con.connected?
        unless con.protocol == @protocol.id
          con.disconnect
          con.protocol = @protocol.id
          con.connect
        end
      else
        con.protocol = @protocol.id
        con.connect
      end
      
      msg = Messages::Request.new(con)
      msg.message = @id

      args.each_with_index do |value, index|
        case @parameters[index].type
          when :int
            then msg.parameters << Types::ShortInteger.new(value)
          when :string
            then msg.parameters << Types::ShortString.new(value)
        end
      end

      msg.submit
      
      response = Messages::Response.new(con)
      response.receive
      
      msg = @protocol.messages.find {|m| m.id == response.message}
      msg.parameters.each_index do |index|
        msg.parameters[index].value = response.parameters[index]
      end
      
      msg
    end
  end
  
  class Parameter
    attr_reader :name, :type
    attr_accessor :value
    
    def initialize(name, type)
      @name, @type = name, type
    end
  end
  
  # here's the magic
  
  module DSL
    def self.extended(base)
      # add the following DSL methods to the classes only when we include the DSL module
      Specification.send :include, SpecificationMethods
      Protocol.send      :include, ProtocolMethods
      Message.send       :include, MessageMethods
    end
    
    def specification(&block)
      @specification = Specification.new(&block)
      @specification.protocols.each do |protocol|
        (self.class == Class ? self : self.class).
          const_set(protocol.name, protocol)
      end
    end
    
    module SpecificationMethods
      def protocol(name, id, &block)
        @protocols << Protocol.new(name, id, &block)
      end
    end
    
    module ProtocolMethods
      def message(name, id, &block)
        @messages << Message.new(name, id, self, &block)
      end
    end
    
    module MessageMethods
      def parameter(name, type)
        @parameters << Parameter.new(name, type)
      end
      
      def string(name)
        parameter(name, :string)
      end

      def int(name)
        parameter(name, :int)
      end
    end
  end
end

module TWP3
  class Server
    def initialize(host, port)
      @host, @port, @spec = host, port
      @socket = TCPServer.new(@host, @port)
    end
    
    def accept
      @socket.accept
    end
  end
end

class Specification
  attr_reader :protocols
  
  def initialize
    @protocols = []
  end
  
  def receive(stream)
    stream.read(5) # "TWP3\n"
    stream.read(1) # "13"
    protocol_id = stream.read(1).ord # "2"
    protocol = @protocols.find {|p| p.id == protocol_id}
    protocol.receive(stream)
  end
end

class Protocol
  attr_reader :name, :id, :messages
  
  def initialize(name, id)
    @name, @id = name, id
    @messages  = []
  end
  
  def receive(stream)
    message_id = stream.read(1).ord - 4
    message = @messages.find {|m| m.id == message_id}
    message.receive(stream)
  end
end

class Message
  attr_accessor :protocol, :id, :text, :parameters, :reply_with
  
  def initialize(name, id, protocol)
    @name, @id, @protocol = name, id, protocol
    @parameters = []
  end
  
  def receive(stream)
    msg = dup
    @parameters.each_index do |i|
      msg.parameters[i] = @parameters[i].receive(stream)
    end
    stream.read(1) # end
    msg
  end
  
  def to_stream
    #stream = "TWP3\n"
    #stream << "#{13.chr}#{2.chr}"
    stream = "#{(@id + 4).chr}"
    @parameters.each do |param|
      stream << param.to_stream
    end
    stream << "#{0.chr}"
  end
end

class Parameter
  attr_accessor :name, :type, :value
  
  def initialize(name, type, value = nil)
    @name, @type, @value = name, type, value
  end
  
  def receive(stream)
    type = stream.read(1).ord
    case type
      when 13
        @value = stream.read(1).ord
      when 17..126
        @value = stream.read(type - 17)
    end
    self
  end

  def to_stream
    case @type
      when :int
        "#{13.chr}#{@value.chr}"
      when :string
        "#{(@value.length + 17).chr}#{@value}"
    end
  end
end

# to encapsulate, can be wrapped by any object (e.g. TWPClient::Echo.Request)
# here on root level
# extend TWP3::DSL
# 
# # sweet, he?
# spec = 
# specification do
#   protocol 'Echo', 2 do
#     message 'Request', 0 do
#       string :text
#     end
#     message 'Reply', 1 do
#       string :text
#       int :number_of_letters
#     end
#   end
# end

# same as:

spec = Specification.new
protocol = Protocol.new('Echo', 2)

message1 = Message.new('Request', 0, protocol)
message1.parameters << Parameter.new('text', :string)

message2 = Message.new('Reply', 1, protocol)
message2.parameters << Parameter.new('text', :string)
message2.parameters << Parameter.new('number_of_letters', :int)

protocol.messages << message1
protocol.messages << message2

spec.protocols << protocol

server = TCPServer.new('localhost', 12345)

while socket = server.accept
  request = spec.receive(socket)
  
  reply = spec.protocols[0].messages[1]
  
  reply.parameters[0].value = request.parameters[0].value
  reply.parameters[1].value = request.parameters[0].value.length
  
  socket.write reply.to_stream
  
  socket.close
end

# server = TCPServer.new('localhost', 12345)
# 
# while s = server.accept
#   puts "client connected"
#   while read = s.gets
#     if read == "TWP3\n"
#       puts "twp3 recognized"
#       break
#     end
#   end
#   protocol_type = s.gets
#   protocol = s.gets.chomp
#   puts "protocol #{protocol}"
#   message = s.gets.chomp
#   puts "message #{message}"
#   params, i = [], 0
#   while read = s.gets
#     if read == "END\n"
#       puts "end of message"
#       break
#     else
#       puts "param #{i} = #{read.chomp}"
#       params[i] = read.chomp
#       i += 1
#     end
#   end
#   result = "protocol #{protocol} message = #{message}"
#   params.each_index do |i|
#     result << "- param #{i} = #{params[i]}"
#   end
#   puts result.inspect
#   s.puts result
#   puts "close connection"
#   s.close
# end