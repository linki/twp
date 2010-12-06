$:.unshift File.expand_path('../../lib', __FILE__)
require 'twp/connection'

require 'protocols/echo'
require 'protocols/rpc'

hpi = TWP::Connection.connect('www.dcl.hpi.uni-potsdam.de', 80)

echo = Echo::Protocol.new(hpi)

request = Echo::Messages::Request.new
request.text = 'blub'

echo.send(request) do |reply|
  puts "#{reply.text} has #{reply.number_of_letters} letters"
end

# rpc = RPC::Protocol.new(hpi)
# 
# request = RPC::Messages::Request.new
# request.request_id = 0
# request.response_expected = 1
# request.operation = 'listdir'
# request.parameters = ['.'] # sequence of string
# 
# rpc.send(request) do |reply|
#   puts '=== DIRS ==='
#   reply.result[0].each do |dir|
#     puts dir
#   end
#   puts '=== FILES ==='
#   reply.result[1].each do |file|
#     puts file
#   end
# end

hpi.disconnect

# TWP::Connection.setup('localhost', 1234)
# #TWP::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)
# 
# request = Echo::Request.new
# request.text = 'martin'
# 
# result = request.invoke(true, true) # with header, no direct response
# 
# puts result.text
# puts result.number_of_letters
# 
# TWP::Connection.setup('localhost', 1234)
# #TWP::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)
# 
# request = RPC::Request.new
# request.request_id = 1
# request.response_expected = 1
# request.operation = 'size'
# request.parameters = ''
# 
# result = request.invoke(true, true) # no header, no direct response
# 
# puts result.request_id
# puts result.result