require 'twp/connection'

module TWP
  module Message
    def self.included(base)
      base.extend ClassMethods    
      base.send :include, InstanceMethods
      base.class_eval do
        class << self
          attr_accessor :_id
          attr_accessor :_protocol
          attr_accessor :_attributes      
        end
        attr_accessor :con
      end
      base._attributes = []
    end
  
    module ClassMethods
      def id(value)
        self._id = value
      end

      def protocol(value)
        self._protocol = value
      end
    
      def attribute(name, type)
        self._attributes << [name, type]
        class_eval do
          attr_accessor name
        end
      end
    
      def receive
        msg = self.new
        msg.receive
        msg
      end
    end
  
    module InstanceMethods
      def initialize
        @con = TWP::Connection.instance
      end

      def invoke(header = true, response_expected = false)
        if header
          con.write_magic_bytes
          con.write_protocol self.class._protocol._id
        end
        @con.write_message_id(self.class._id)
        self.class._attributes.each do |attr|
          @con.write_generic(instance_variable_get("@#{attr[0]}"), attr[1])
        end
        @con.write_end_of_msg
        self.class._protocol.receive(false) if response_expected
      end
    
      def receive
        self.class._attributes.each do |attr|
          instance_variable_set("@#{attr[0]}", @con.read_generic)
        end
      end
    end
  end
end