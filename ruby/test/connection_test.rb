$:.unshift File.expand_path('../lib', File.dirname(__FILE__))
require 'twp/connection'

require 'minitest/unit'
require 'minitest/autorun'
require 'mocha'

class ConnectionTest < MiniTest::Unit::TestCase
  def setup
    @con = TWP::Connection.instance
    @socket = @con.socket = mock
  end
  
  def test_write_magic_bytes_sends_twps_magic_bytes
    @socket.expects(:write).with("TWP3\n")
    @con.write_magic_bytes
  end

  def test_read_integer_reads_short_integer
    read_int = sequence('read_int')
    @socket.expects(:read).with(1).returns("\x0D").in_sequence(read_int) # 13
    @socket.expects(:read).with(1).returns("\x64").in_sequence(read_int) # 100    
    assert_equal 100, @con.read_integer
  end

  def test_write_integer_writes_short_integer
    @socket.expects(:write).with("\x0D") # 13
    @socket.expects(:write).with("\x64") # 100
    @con.write_integer 100
  end

  def test_read_integer_reads_long_integer
    @socket.expects(:read).with(1).returns("\x0E") #14
    @socket.expects(:read).with(4).returns("\x00\x00\x75\x30") # 30000
    assert_equal 30000, @con.read_integer
  end
  
  def test_write_integer_writes_long_integer
    @socket.expects(:write).with("\x0E") # 14
    @socket.expects(:write).with("\x00\x00\x75\x30") # 0 0 117 48 = 30000
    @con.write_integer 30000
  end
  
  def test_read_string_reads_short_string
    @socket.expects(:read).with(1).returns("\x18") # 24
    @socket.expects(:read).with(7).returns("awesome")
    assert_equal "awesome", @con.read_string
  end
  
  def test_write_string_writes_short_string
    @socket.expects(:write).with("\x18") # 24
    @socket.expects(:write).with("awesome")
    @con.write_string "awesome"
  end  

  def test_read_string_reads_long_string
    @socket.expects(:read).with(1).returns("\x7F") # 127
    @socket.expects(:read).with(4).returns("\x00\x00\x02\xBC") # 700
    @socket.expects(:read).with(700).returns("awesome"*100)
    assert_equal "awesome"*100, @con.read_string
  end

  def test_write_string_writes_long_string
    @socket.expects(:write).with("\x7F") # 127
    @socket.expects(:write).with("\x00\x00\x02\xBC") # 700    
    @socket.expects(:write).with("awesome"*100)
    @con.write_string "awesome"*100
  end
  
  def test_read_binary_reads_short_binary
    read_bin = sequence('read_bin')
    @socket.expects(:read).with(1).returns("\x0F").in_sequence(read_bin) # 15
    @socket.expects(:read).with(1).returns("\x08").in_sequence(read_bin) # 8
    @socket.expects(:read).with(8).returns("\x00\x01\x02\x03\x04\x05\x06\x07").in_sequence(read_bin) # 0 1 2 3 4 5 6 7
    assert_equal "\x00\x01\x02\x03\x04\x05\x06\x07", @con.read_binary
  end
  
  def test_write_binary_writes_short_binary
    @socket.expects(:write).with("\x0F") # 15
    @socket.expects(:write).with("\x08") # 8
    @socket.expects(:write).with("\x00\x01\x02\x03\x04\x05\x06\x07")
    @con.write_binary "\x00\x01\x02\x03\x04\x05\x06\x07"
  end  

  def test_read_binary_reads_long_binary
    @socket.expects(:read).with(1).returns("\x10") # 16
    @socket.expects(:read).with(4).returns("\x00\x00\x03\x20") # 800
    @socket.expects(:read).with(800).returns("\x00\x01\x02\x03"*200)
    assert_equal "\x00\x01\x02\x03"*200, @con.read_binary
  end

  def test_write_binary_writes_long_binary
    @socket.expects(:write).with("\x10") # 16
    @socket.expects(:write).with("\x00\x00\x03\x20") # 800    
    @socket.expects(:write).with("\x00\x01\x02\x03"*200)
    @con.write_binary "\x00\x01\x02\x03"*200
  end  
end