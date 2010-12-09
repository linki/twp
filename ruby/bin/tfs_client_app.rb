$:.unshift File.expand_path('../../lib', __FILE__)
require 'tfs/client'

require 'net/dns/resolver'
require 'net/dns/rr/srv' #fail

packet = Net::DNS::Resolver.start('tfs.dcl.hpi.uni-potsdam.de', Net::DNS::SRV)
packet.answer.any? do |res|
  @host = res.host
  @port = res.port
end

tfs = TFS::Client.new(@host, @port)

list_result = tfs.listdir(['.']) # TFS::Path.new('.')

puts '=== DIRS ==='
list_result.directories.each do |dir|
  puts dir
end

puts '=== FILES ==='
list_result.files.each do |file|
  puts file
end
