require 'twp/protocol'
require 'twp/message'

module Echo
  module Messages
    class Request
      include TWP::Message
      
      @id = 0
      
      @parameters = {
        'text' => :string
      }
      
      attr_accessor :text
    end
    
    class Reply
      include TWP::Message
      
      @id = 1
      
      @parameters = {
        'text' => :string,
        'number_of_letters' => :int
      }
      
      attr_accessor :text
      attr_accessor :number_of_letters   
    end
  end

  class Protocol
    include TWP::Protocol
    
    @id = 2
    
    @messages = {
      0 => Messages::Request,
      1 => Messages::Reply
    }
  end
end