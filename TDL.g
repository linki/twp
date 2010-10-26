grammar TDL;

ALPHA 	: 'A'..'Z'|'a'..'z';
DIGIT 	: 0..9;
LETTER 	: ALPHA | '_';
WS  : (' '|'\r'|'\n')+ {$channel = HIDDEN;} ;
identifier 
	: LETTER (LETTER | DIGIT)*;
number 
   	: DIGIT+;
   	
type 	
	: primitiveType | identifier | ('any' 'defined' 'by' identifier);
primitiveType 
   	: 'int' | 'string' | 'binary' | 'any';
   	
typedef 	
	:	 structdef | sequencedef | uniondef | forwarddef;
structdef 
   	: 'struct' identifier ( '=' 'ID' number )? '{' field+ '}';
field 
   	: 'optional'? type identifier ';';
sequencedef 
   	: 'sequence' '<' type '>' identifier ';';
uniondef 
   	: 'union' identifier '{' casedef+ '}';
casedef 	
   	: 'case' number ':' type identifier ';';
forwarddef 
   	: 'typedef' identifier ';';
   
messagedef 
   	: 'message' identifier '=' ( '0'..'7' | 'ID' number ) '{' field* '}';
protocol 
   	: 'protocol' identifier '=' 'ID' number '{' protocolelement* '}';
protocolelement 
   	: typedef | messagedef;
specification 
   	: (protocol | messagedef | structdef)*;