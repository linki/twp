require 'rubygems'
require 'treetop'

Treetop.load File.expand_path('../../grammars/twp.treetop', __FILE__)

if ARGV.size < 1
  STDERR.puts 'Give me a file to parse!'
  STDERR.puts 'USAGE: acceptor.rb FILEPATH'
  exit
end

content = File.read(ARGV[0])

parser = TWPParser.new
puts parser.parse(content)  # => Treetop::Runtime::SyntaxNode

# returns nil if source doesn't match the grammar, otherwise Treetop::Runtime::SyntaxNode
