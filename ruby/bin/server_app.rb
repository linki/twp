$:.unshift File.expand_path('../lib', File.dirname(__FILE__))
require 'twp/server'

require 'protocols/echo'
require 'protocols/rpc'

server = TWP::Server.new('localhost', 1234)

server.register Echo
server.handler_for Echo::Request do |request|
  reply = Echo::Reply.new
  reply.text = request.text
  reply.number_of_letters = request.text.length
  reply    
end

server.register RPC
server.handler_for RPC::Request do |request|
  reply = RPC::Reply.new
  reply.request_id = request.request_id
  reply.result = 42
  reply
end

server.start