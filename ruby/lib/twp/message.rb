module TWP
  module Message
    def self.included(base)
      base.extend ClassMethods      
      base.send :include, InstanceMethods
      base.singleton_class.class_eval do
        attr_accessor :id
        attr_accessor :parameters
      end
    end
    
    module ClassMethods
      def receive(con)
        new.tap { |m| m.receive(con) }
      end
    end
    
    module InstanceMethods
      def send(con)
        con.write_message_id self.class.id
        self.class.parameters.each_pair do |name, type|
          con.write_generic(instance_variable_get("@#{name}"), type)
        end
        con.write_end_of_msg
      end

      def receive(con)
        self.class.parameters.each_pair do |name, type|
          instance_variable_set("@#{name}", con.read_generic)
        end
        con.read_end_of_msg
      end
    end
  end
end