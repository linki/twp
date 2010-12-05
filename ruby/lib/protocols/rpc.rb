require 'twp/protocol'
require 'twp/message'

module RPC
  module Messages
    class Request
      include TWP::Message
      
      @id = 0
      
      @parameters = {
        'request_id' => :int,
        'response_expected' => :int,
        'operation' => :string,
        'parameters' => :any
      }
      
      attr_accessor :request_id
      attr_accessor :response_expected
      attr_accessor :operation
      attr_accessor :parameters
    end
    
    class Reply
      include TWP::Message
      
      @id = 1
      
      @parameters = {
        'request_id' => :int,
        'result' => :any
      }
      
      attr_accessor :request_id
      attr_accessor :result    
    end
    
    class CancelRequest
      include TWP::Message
      
      @id = 2
      
      @parameters = {
        'request_id' => :int
      }
      
      attr_accessor :request_id 
    end
    
    class CloseConnection
      include TWP::Message
      
      @id = 1
      
      @parameters = {}
    end
  end

  class Protocol
    include TWP::Protocol

    @id = 1

    @messages = {
      0 => Messages::Request,
      1 => Messages::Reply,
      2 => Messages::CancelRequest,
      4 => Messages::CloseConnection
    }
  end
end