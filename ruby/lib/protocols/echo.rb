# generated file

require 'twp/protocol'
require 'twp/message'

module Echo
  extend TWP::Protocol
  
  id 2

  class Request
    include TWP::Message
    
    id 0
    protocol Echo
    attribute :text, :string
  end
  
  class Reply
    include TWP::Message
    
    id 1
    protocol Echo
    attribute :text, :string
    attribute :number_of_letters, :int
  end

  self._messages = { 0 => Request, 1 => Reply }
end
