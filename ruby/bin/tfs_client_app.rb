$:.unshift File.expand_path('../../lib', __FILE__)
require 'tfs_client'

tfs = TFSClient.new('www.dcl.hpi.uni-potsdam.de', 80)
list_result = tfs.listdir

puts '=== DIRS ==='
list_result.directories.each do |dir|
  puts dir
end

puts '=== FILES ==='
list_result.files.each do |file|
  puts file
end
