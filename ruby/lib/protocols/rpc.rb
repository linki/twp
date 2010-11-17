# generated file

require 'twp/protocol'
require 'twp/message'

module RPC
  extend TWP::Protocol
  
  id 1

  class Request
    include TWP::Message
    
    id 0
    protocol RPC
    attribute :request_id, :int
    attribute :response_expected, :int
    attribute :operation, :string
    attribute :parameters, :string
  end
  
  class Reply
    include TWP::Message
    
    id 1
    protocol RPC
    attribute :request_id, :int
    attribute :result, :int
  end
  
  class CancelRequest
    include TWP::Message
    
    id 2
    protocol RPC
    attribute :request_id, :int
  end
  
  class CloseConnection
    include TWP::Message
    
    id 4
    protocol RPC
  end  

  self._messages = { 0 => Request, 1 => Reply, 2 => CancelRequest, 4 => CloseConnection, }
end
