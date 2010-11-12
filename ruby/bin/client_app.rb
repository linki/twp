$:.unshift File.expand_path('../lib', File.dirname(__FILE__))
require 'twp/connection'

require 'protocols/echo'
require 'protocols/rpc'

TWP::Connection.setup('localhost', 1234)
#TWP::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)

request = Echo::Request.new
request.text = 'martin'

result = request.invoke(true, true) # with header, no direct response

puts result.text
puts result.number_of_letters

TWP::Connection.setup('localhost', 1234)
#TWP::Connection.setup('www.dcl.hpi.uni-potsdam.de', 80)

request = RPC::Request.new
request.request_id = 1
request.response_expected = 1
request.operation = 'size'
request.parameters = ''

result = request.invoke(true, true) # no header, no direct response

puts result.request_id
puts result.result