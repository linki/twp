require 'rubygems'
require 'treetop'

module TWP
  class SpecificationNode < Treetop::Runtime::SyntaxNode
    def to_array
      return self.elements.map {|e| e.to_array}
    end
  end
  
  class ProtocolNode < Treetop::Runtime::SyntaxNode
    def to_array
      return self.elements.map {|e| e.to_array}
    end
  end
  
  class MessageNode < Treetop::Runtime::SyntaxNode
    def to_array
      return self.elements.map {|e| e.to_array}
    end
  end  

  class ProtocolElementNode < Treetop::Runtime::SyntaxNode
  end
  
  class StructdefNode < Treetop::Runtime::SyntaxNode
  end   
  
  class TypedefNode < Treetop::Runtime::SyntaxNode
  end
  
  class SequencedefNode < Treetop::Runtime::SyntaxNode
  end
  class UniondefNode < Treetop::Runtime::SyntaxNode
  end
  
  class CasedefNode < Treetop::Runtime::SyntaxNode
  end
  class ForwarddefNode < Treetop::Runtime::SyntaxNode
  end
 
  class TypeNode < Treetop::Runtime::SyntaxNode
  end
 
  class PrimitiveTypeNode < Treetop::Runtime::SyntaxNode
  end
  
  class FieldNode < Treetop::Runtime::SyntaxNode
  end

  class LetterNode < Treetop::Runtime::SyntaxNode
  end
  
  class AlphaNode < Treetop::Runtime::SyntaxNode
  end
  
  class DigitNode < Treetop::Runtime::SyntaxNode
  end
  
  class SpaceNode < Treetop::Runtime::SyntaxNode
  end
   class TypeNode < Treetop::Runtime::SyntaxNode
   end
  class IdentifierNode < Treetop::Runtime::SyntaxNode
    def to_array
      return self.text_value
    end
  end

  class NumberNode < Treetop::Runtime::SyntaxNode
    def to_array
      return self.text_value.to_i
    end
  end
  
  class CommentNode < Treetop::Runtime::SyntaxNode
  end
    
  class SpaceNode < Treetop::Runtime::SyntaxNode
  end  
end

Treetop.load File.expand_path('../../grammars/twp.treetop', __FILE__)

if ARGV.size < 1
  STDERR.puts 'Give me a file to parse!'
  STDERR.puts 'USAGE: acceptor.rb FILEPATH'
  exit
end

content = File.read(ARGV[0])

parser = TWPParser.new
tree = parser.parse(content)  # => Treetop::Runtime::SyntaxNode
#puts tree.inspect

def clean_tree(root_node)
  return if root_node.elements.nil?
  root_node.elements.delete_if {|node| node.class.name == "Treetop::Runtime::SyntaxNode" }
  root_node.elements.each {|node| clean_tree(node) }
end


#clean_tree(tree)
puts tree.inspect

#puts tree.to_array.inspect

# returns nil if source doesn't match the grammar, otherwise Treetop::Runtime::SyntaxNode
