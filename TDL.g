grammar TDL;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ { $channel = HIDDEN; };

ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;
SEVEN 	: '0'..'7';
INT	:	'0'..'9'+;
identifier 
	: ID;
	
seven	:	
	SEVEN;
	
number 
   	: INT;
   	
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
   	: 'message' identifier '=' ( seven | 'ID' number ) '{' field* '}';//'0'..'7'
protocol 
   	: 'protocol' identifier '=' 'ID' number '{' protocolelement* '}';
protocolelement 
   	: typedef | messagedef;
specification 
   	: (protocol | messagedef | structdef)* EOF;