require 'socket'

module TWP
  class Connection
    MAGIC_BYTES = "TWP3\n"

    attr_accessor :socket
    
    def self.connect(host, port)
      new(host, port).tap { |c| c.connect }
    end
    
    def initialize(host, port)
      @host, @port = host, port
    end
    
    def connect
      @socket = TCPSocket.new(@host, @port)
    end
    
    def connected?
      @socket && !@socket.closed?
    end
    
    def disconnect
      @socket.close
    end
    
    def read_generic(type = read_byte)
      case type
        when 13..14
          read_integer(type)
        when 15..16
          read_binary(type)
        when 17..127
          read_string(type)
        else
          raise Exception
      end
    end
    
    def write_generic(value, type)
      case type
        when :int
          write_integer(value)
        when :binary
          write_binary(value)
        when :string
          write_string(value)
        else
          raise Exception
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
      write_integer protocol
    end
    
    def read_message_id
      read_byte - 4
    end
    
    def write_message_id(message_id)
      write_byte message_id + 4
    end
    
    def read_integer(type = read_byte)
      case type
        when 13
          read_byte
        when 14
          read_four_bytes
        else
          raise Exception
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
    
    def read_binary(type = read_byte)
      case type
        when 15
          length = read_byte
          read(length)
        when 16
          length = read_four_bytes
          read(length)
        else
          raise Exception
      end
    end
    
    def write_binary(value)
      if (0..255).include?(value.length)
        write_byte 15
        write_byte value.length
        write value
      else
        write_byte 16
        write_four_bytes value.length        
        write value
      end
    end 
    
    def read_end_of_msg
      read_byte
    end
    
    def write_end_of_msg
      write_byte 0
    end

    def read_byte
      read(1).ord
    end

    def write_byte(byte)
      write byte.chr
    end
    
    def read_four_bytes
      # Treat four characters as an unsigned long in network byte order
      # "\x00\x00\x00\xA2".unpack('N') => [162]
      read(4).unpack('N').first
    end
    
    def write_four_bytes(value)
      # [162].pack('N') => "\x00\x00\x00\xA2"
      write [value].pack('N')
    end
    
    def read(*args, &block)
      @socket.read(*args, &block)
    end
    
    def write(*args, &block)
      @socket.write(*args, &block)
    end
  end
end