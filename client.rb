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
      @socket = TCPSocket.new(@host, @port)
      @socket.write MAGIC_BYTES
      @socket.write @protocol
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
  
  module Message
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
end

con = TWP3::Connection.new('www.dcl.hpi.uni-potsdam.de', 80)
con.protocol = 2
con.connect

msg = TWP3::Message::Request.new(con)
msg.message = 0
msg.parameters << TWP3::Types::ShortString.new('test')
msg.submit

response = TWP3::Message::Response.new(con)
response.receive

response.print

con.disconnect
