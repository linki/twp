$:.unshift File.expand_path('../../lib', __FILE__)
require 'twp/connection'

module Echo
  class Protocol
  end
  
  module Messages
    class Request
      @id = 0
      @protocol = Echo::Protocol
      
      @parameters = {
        'text' => :string
      }

      class << self
        attr_accessor :id
        attr_accessor :protocol
        attr_accessor :parameters
      end
      
      attr_accessor :text
      
      def self.receive(con)
        new.tap { |m| m.receive(con) }
      end

      def send(con)
        con.write_magic_bytes
        con.write_protocol self.class.protocol.id
        con.write_message_id self.class.id
        self.class.parameters.each_pair do |name, type|
          con.write_generic(instance_variable_get("@#{name}"), type)
        end
        con.write_end_of_msg
      end

      def receive(con)
        self.class.parameters.each_key do |name|
          instance_variable_set("@#{name}", con.read_generic)
        end
        con.read_end_of_msg
      end
    end
    
    class Reply
      @id = 1
      @protocol = Echo::Protocol
      
      @parameters = {
        'text' => :string,
        'number_of_letters' => :int
      }

      class << self
        attr_accessor :id
        attr_accessor :protocol
        attr_accessor :parameters
      end
      
      attr_accessor :text
      attr_accessor :number_of_letters
      
      def self.receive(con)
        new.tap { |m| m.receive(con) }
      end

      def send(con)
        con.write_magic_bytes
        con.write_protocol self.class.protocol.id
        con.write_message_id self.class.id
        self.class.parameters.each_pair do |name, type|
          con.write_generic(instance_variable_get("@#{name}"), type)
        end
        con.write_end_of_msg
      end
      
      def receive(con)
        self.class.parameters.each_key do |name|
          instance_variable_set("@#{name}", con.read_generic)
        end
        con.read_end_of_msg
      end      
    end
  end

  class Protocol
    @id = 2
    
    @messages = {
      0 => Messages::Request,
      1 => Messages::Reply
    }
    
    class << self
      attr_accessor :id
      attr_accessor :messages
    end
    
    def initialize(connection = nil)
      @connection = connection
    end
    
    def send(message, connection = @connection)
      message.send(connection)
      yield receive(connection) if block_given?
    end
    
    def receive(connection = @connection)
      message_id = connection.read_message_id
      message_klass = self.class.messages[message_id]
      message_klass.receive(connection)
    end
  end
end

hpi = TWP::Connection.connect('www.dcl.hpi.uni-potsdam.de', 80)

echo = Echo::Protocol.new(hpi)

request = Echo::Messages::Request.new
request.text = 'martin'

echo.send(request) do |reply|
  puts reply.inspect
end

hpi.disconnect

# TWP::Connection.setup('localhost', 1234)
# #TWP::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)
# 
# request = Echo::Request.new
# request.text = 'martin'
# 
# result = request.invoke(true, true) # with header, no direct response
# 
# puts result.text
# puts result.number_of_letters
# 
# TWP::Connection.setup('localhost', 1234)
# #TWP::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)
# 
# request = RPC::Request.new
# request.request_id = 1
# request.response_expected = 1
# request.operation = 'size'
# request.parameters = ''
# 
# result = request.invoke(true, true) # no header, no direct response
# 
# puts result.request_id
# puts result.result