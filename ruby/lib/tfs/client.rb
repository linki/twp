require 'twp/connection'
require 'protocols/rpc'

require 'ostruct'

module TFS
  class ListResult < Struct.new(:directories, :files) # looks good
  end

  class FileList < Array # Sequence.new(String)
  end

  class Client
    attr_accessor :rpc
    
    def initialize(host, port)
      con = TWP::Connection.connect(host, port)
      @rpc = RPC::Protocol.new(con)
      at_exit { con.disconnect }
    end
  
    def listdir(path)
      request = RPC::Messages::Request.new
      request.request_id = 0
      request.response_expected = 1
      request.operation = 'listdir'
      request.parameters = path

      result = ListResult.new
      result.directories = FileList.new
      result.files = FileList.new
        
      @rpc.send(request) do |reply|
        reply.result[0].each do |dir|
          result.directories << dir
        end
        reply.result[1].each do |file|
          result.files << file
        end
      end

      result
    end
  end
end