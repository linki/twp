require 'singleton'
require 'socket'

module TWP
  class Connection
    include Singleton
    attr_accessor :socket
    
    MAGIC_BYTES = "TWP3\n"
    
    def self.setup(host, port)
      con = self.instance
      con.setup(host, port)
    end
    
    def setup(host, port)
      @host, @port = host, port
      connect
      at_exit { disconnect }
      self
    end
    
    def connect
      @socket = TCPSocket.new(@host, @port)
    end
    
    def disconnect
      @socket.close if @socket && !@socket.closed?
    end
    
    def read_generic(type = read_byte)
      case type
        when 13..14
          read_integer(type)
        when 17..127
          read_string(type)
      end
    end
    
    def write_generic(value, type)
      case type
        when :int
          write_integer(value)
        when :string
          write_string(value)
      end
    end
    
    def read_magic_bytes
      read(5) == MAGIC_BYTES
    end
    
    def write_magic_bytes
      write MAGIC_BYTES
    end
    
    def read_protocol
      read_integer
    end
    
    def write_protocol(protocol)
      write_integer(protocol)
    end
    
    # extension
    def read_message_id
      read_byte - 4
    end
    
    # extension    
    def write_message_id(message_id)
      write_byte message_id + 4
    end
    
    def read_integer(type = read_byte)
      case type
        when 13
          read_byte
        when 14
          read_four_bytes
      end
    end
    
    def write_integer(value)
      if (-128..127).include?(value)
        write_byte 13
        write_byte value
      else
        write_byte 14
        write_four_bytes value
      end
    end
    
    def read_string(type = read_byte)
      if (17..126).include?(type)
        read(type - 17)
      else
        length = read_four_bytes
        read(length)
      end
    end
    
    def write_string(text)
      if text.length > 109
        write_byte 127
        write_four_bytes text.length
        write text
      else
        write_byte text.length + 17
        write text
      end
    end
    
    def read_end_of_msg
      read_byte
    end
    
    def write_end_of_msg
      write_byte 0
    end
    
    def read_four_bytes
      # "\x00\x00\x00\xA2".unpack('N') => [162]
      # Treat four characters as an unsigned long in network byte order
      read(4).unpack('N').first
    end
    
    def write_four_bytes(value)
      # [162].pack('N') => "\x00\x00\x00\xA2"
      write [value].pack('N')
    end

    def read_byte
      read(1).ord
    end
    
    def write_byte(byte)
      write byte.chr
    end
    
    def read(*args, &block)
      @socket.read(*args, &block)
    end
    
    def write(*args, &block)
      @socket.write(*args, &block)
    end
  end
end