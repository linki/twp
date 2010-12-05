module TWP
  module Protocol
    def self.included(base)
      base.send :include, InstanceMethods
      base.singleton_class.class_eval do
        attr_accessor :id
        attr_accessor :messages
      end
    end
    
    module InstanceMethods
      def initialize(connection = nil)
        @connection = connection
      end
    
      def send(message, connection = @connection)
        connection.write_magic_bytes
        connection.write_protocol self.class.id
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
end