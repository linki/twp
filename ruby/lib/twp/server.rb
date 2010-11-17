require 'twp/connection'

module TWP
  class Server
    attr_accessor :handlers
    attr_accessor :protocols
    
    def initialize(host, port)
      @host, @port = host, port
      @handlers, @protocols = {}, {}
    end
    
    def register(protocol)
      @protocols[protocol._id] = protocol
    end
    
    def start
      con = TWP::Connection.instance
      @server = TCPServer.new(@host, @port)
      while con.socket = @server.accept
        con.read_magic_bytes
        protocol_id = con.read_protocol
        request = @protocols[protocol_id].receive(false)

        reply = handle(request)
        reply.invoke(false, false) # no header, no response

        con.disconnect
      end
    end
    
    def handler_for(message, &block)
      @handlers[message] = block
    end

    def handle(message)
      @handlers[message.class].call(message)
    end  
  end
end