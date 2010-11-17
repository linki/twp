require 'twp/connection'

module TWP
  module Protocol
    def self.extended(base)
      base.class_eval do
        class << self
          attr_accessor :_id
          attr_accessor :_messages
        end
      end
    end
  
    def id(value)
      self._id = value
    end

    def receive(header = true)
      con = TWP::Connection.instance
      if header
        con.read_magic_bytes
        con.read_protocol
      end
      message_id = con.read_message_id
      msg = self._messages[message_id].receive
      con.read_end_of_msg
      msg
    end
  end
end