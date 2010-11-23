grammar TDL;

options {
	output=AST;
}

@header {
import twp.generator.metamodel.*;
}

WHITESPACE 
	: ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ { $channel = HIDDEN; };

COMMENT
	: '/*' (options {greedy=false;} : .)* '*/' {$channel = HIDDEN; };

ID  
	: ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;


fragment SEVEN 	
	: '0'..'7';

SEVENUP 
	: SEVEN;

INT	
	: (SEVEN | '8' | '9')+;

identifier 
	: ID;
	
seven	
	: SEVENUP;
	
number 
   	: INT | SEVENUP;
   	
type returns[Type type]
@init{$type = new Type();}
	: primitiveType {$type.primitive = true; $type.setName($primitiveType.text);}| 
	identifier {$type.setName($identifier.text);}| 
	('any' 'defined' 'by' identifier {$type.anyDefinedBy = true; $type.setName($identifier.text);});
	
primitiveType 
   	: 'int' | 'string' | 'binary' | 'any';
   	
typedef [Protocol protocol]	
	: st=structdef {$protocol.structs.add(st.struct);}| 
	s=sequencedef {$protocol.sequences.add(s.sequence);}| 
	u=uniondef {$protocol.unions.add(u.union);}| 
	f=forwarddef {$protocol.forwards.add(f.forward);};
	
structdef returns[Struct struct]
@init{$struct = new Struct();}
   	: 'struct' identifier {$struct.setName($identifier.text);} 
   	( '=' 'ID' number {$struct.id = Integer.parseInt($number.text);})? 
   	'{' (f=field {$struct.fields.add(f.field);})+ '}';
   	
field returns[Field field]
@init{$field = new Field();}
   	: ('optional' {$field.optional = true;})? t=type identifier ';'
   	{$field.type = t.type;
   	$field.setName($identifier.text);};
   	
   	
sequencedef returns[Sequence sequence]
@init{$sequence = new Sequence();}
   	: 'sequence' '<' t=type '>' identifier ';'
   	{$sequence.type = t.type;
   	$sequence.setName($identifier.text);};
   	
uniondef returns[Union union]
@init{$union = new Union();}
   	: 'union' identifier '{' (c=casedef {$union.cases.add(c.caze);})+ '}'
   	{$union.setName($identifier.text);};
   	
casedef returns[Case caze]
@init{$caze = new Case();}
   	: 'case' number ':' t=type identifier ';'
   	{$caze.id = Integer.parseInt($number.text);
   	$caze.type = t.type;
   	$caze.setName($identifier.text);};
   	
forwarddef returns[Forward forward]
@init{$forward = new Forward();}
   	: 'typedef' identifier ';'
   	{$forward.setName($identifier.text);};
   
messagedef returns[Message message]
@init{$message = new Message();}
   	: 'message' identifier {$message.setName($identifier.text);}'=' 
   	( seven {$message.id = Integer.parseInt($seven.text);}| 'ID' number {$message.id = Integer.parseInt($number.text);}) 
   	'{' (f=field {$message.fields.add(f.field);})* '}';//'0'..'7'
   	
protocol returns[Protocol protocol]
@init{$protocol = new Protocol();}
   	: 'protocol' identifier '=' 'ID' number '{' (protocolelement[$protocol])* '}'
   	{$protocol.setName($identifier.text);
   	$protocol.id = Integer.parseInt($number.text);};

protocolelement [Protocol protocol]
   	: typedef[$protocol] | 
   	m=messagedef {$protocol.messages.add(m.message);};

specification returns[Specification spec]
@init{$spec = new Specification();}
   	: (p=protocol {$spec.protocols.add(p.protocol);}| 
   	m=messagedef {$spec.messages.add(m.message);}| 
   	s=structdef {$spec.structs.add(s.struct);})* EOF;