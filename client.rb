require 'socket'

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
    def initialize(host, port = 80)
      @host, @port = host, port
    end
    
    def protocol=(id)
      @protocol = Types::ShortInteger.new(id)
    end
    
    def connect
      begin
        @socket = TCPSocket.new(@host, @port)
        @socket.write MAGIC_BYTES
        @socket.write @protocol
      rescue Exception
        @socket.close
        exit(1)
      end
    end
    
    def disconnect
      @socket.close
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
      
      def print
        puts "Response"
        puts "========"
        puts "Message: #{@message}"
        puts "Parameters:"
        @parameters.each do |param|
          puts "- #{param}"
        end
      end
    end
  end
  
  # new stuff
  
  class Specification
    attr_reader :protocols
    
    def initialize(&block)
      @protocols = []
      instance_exec(&block)
    end
  end
  
  class Protocol
    attr_reader :messages
    
    def initialize(name, id, &block)
      @name, @id = name, id
      @messages  = []
      instance_exec(&block)
    end
  end
  
  class Message
    attr_reader :parameters
    
    def initialize(name, id, &block)
      @name, @id = name, id
      @parameters = []
      instance_exec(&block)
    end
  end
  
  class Parameter
    def initialize(name, type)
      @name, @type = name, type
    end
  end
  
  # here's the magic
  
  module DSL
    def self.included(base)
      # add the following DSL methods to the classes only when we include the DSL module
      Specification.send :include, SpecificationMethods
      Protocol.send      :include, ProtocolMethods
      Message.send       :include, MessageMethods
    end
    
    def specification(&block)
      Specification.new(&block)
    end
    
    module SpecificationMethods
      def protocol(name, id, &block)
        @protocols << Protocol.new(name, id, &block)
      end
    end
    
    module ProtocolMethods
      def message(name, id, &block)
        @messages << Message.new(name, id, &block)
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

# only to encapsulate
include TWP3::DSL

# sweet?!
spec =
specification do
  protocol 'Echo', 2 do
    message 'Request', 0 do
      string :text
      # short for parameter :text, :string
    end
    message 'Reply', 1 do
      string :text
      int :number_of_letters
    end
  end
end

# same as:

# message1 = TWP3::Message.new('Request', 0)
# message1.parameters << TWP3::Parameter.new('text', :string)
# 
# message2 = TWP3::Message.new('Reply', 1)
# message2.parameters << TWP3::Parameter.new('text', :string)
# message2.parameters << TWP3::Parameter.new('number_of_letters', :int)
# 
# protocol = TWP3::Protocol.new('Echo', 2)
# protocol.messages << message1
# protocol.messages << message2
# 
# spec = TWP3::Specification.new
# spec.protocols << protocol

puts spec.inspect

# so the parsed result of that should be like the following?
class Echo
  def Request(text)
    [text, text.length]
  end

  def Reply(text, nr_of_letters)
    nil
  end
end

# old stuff that works
con = TWP3::Connection.new('www.dcl.hpi.uni-potsdam.de', 80)
con.protocol = 2
con.connect

msg = TWP3::Messages::Request.new(con)
msg.message = 0
msg.parameters << TWP3::Types::ShortString.new('test')
msg.submit

response = TWP3::Messages::Response.new(con)
response.receive

response.print

con.disconnect
