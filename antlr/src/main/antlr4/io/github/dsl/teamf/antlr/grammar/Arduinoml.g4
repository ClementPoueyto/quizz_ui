grammar Arduinoml;


/******************
 ** Parser rules **
 ******************/

root            :   declaration EOF;

declaration     :   'application' name=IDENTIFIER;


/*****************
 ** Lexer rules **
 *****************/
PORT_NUMBER     :   [1-9] | '11' | '12';
NUMBER          :   [0-9]+;
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;


/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
