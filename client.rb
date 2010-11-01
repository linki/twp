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

# to encapsulate, can be wrapped by any object (e.g. TWPClient::Echo.Request)
# here on root level
extend TWP3::DSL

# sweet, he?
specification do
  protocol 'Echo', 2 do
    message 'Request', 0 do
      string :text
    end
    message 'Reply', 1 do
      string :text
      int :number_of_letters
    end
  end
end

# same as:

# spec = TWP3::Specification.new
# protocol = TWP3::Protocol.new('Echo', 2)
# 
# message1 = TWP3::Message.new('Request', 0, protocol)
# message1.parameters << TWP3::Parameter.new('text', :string)
# 
# message2 = TWP3::Message.new('Reply', 1, protocol)
# message2.parameters << TWP3::Parameter.new('text', :string)
# message2.parameters << TWP3::Parameter.new('number_of_letters', :int)
# 
# protocol.messages << message1
# protocol.messages << message2
# 
# spec.protocols << protocol
# 
# Echo = protocol
#
# puts spec.inspect

# now go:

TWP3::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)

reply = Echo.Request('test')

puts "#{reply.name} = ID #{reply.id}"
reply.parameters.each do |param|
  puts "- #{param.type} #{param.name} = #{param.value}"
end

# @socket = TCPSocket.new('localhost', 12345)
# puts @socket.read(4).inspect
# @socket.close

# class Echo
#   def Request(text)
#     ['asd', 3]
#   end
# end