grammar TDL;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ { $channel = HIDDEN; };

DIGIT 	: '0'..'9';
identifier 
	: 'text' | 'number_of_letters' | 'Echo' | 'Request' | 'Reply';
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
   	: 'message' identifier '=' ( DIGIT | 'ID' number ) '{' field* '}';
protocol 
   	: 'protocol' identifier '=' 'ID' number '{' protocolelement* '}';
protocolelement 
   	: typedef | messagedef;
specification 
   	: (protocol | messagedef | structdef)* EOF;