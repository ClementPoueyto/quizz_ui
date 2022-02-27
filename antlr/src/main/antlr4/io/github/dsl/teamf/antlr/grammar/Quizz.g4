grammar Quizz;

/******************
 ** Parser rules **
 ******************/

root            :   appDeclaration declaration* EOF;

appDeclaration  :   'Application' name=WORD 'from quiz' quizPath=STRING appAttributes;

appAttributes   :   (layoutAttribute themeAttribute) | (layoutAttribute themeAttribute);
layoutAttribute :   'uses layout' layoutName=IDENTIFIER;
themeAttribute  :   'uses theme' themeName=IDENTIFIER;

declaration     :   themeDeclaration;
themeDeclaration:   IDENTIFIER 'is theme with primary color' primary=COLOR'secondary color' secondary=COLOR'font family' fontFamily=FONTFAM;

/*****************
 ** Lexer rules **
 *****************/
 
NUMBER          :   [0-9]+;
TIME            : [0-2][0-3]':'[0-5][0-9]':'[0-5][0-9];
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+;
SIZE            :   'XXSMALL' | 'XSMALL' | 'SMALL' | 'MEDIUM' | 'LARGE' | 'XLARGE' | 'AUTO' | 'FULL';
BORDERSTYLE     :   'SOLID' | 'DASHED' | 'DOTTED' | 'DOUBLE' | 'RIDGE';
TEXT            :   UPPERCASE (IDENTIFIER WS* NEWLINE)+;
LETTER          :   (LOWERCASE|UPPERCASE);
CHAR            :   (NUMBER|LETTER);
HEX             :   '#' CHAR+;
COLOR           :   'RED' | 'BLUE' | 'GREEN' | 'YELLOW' | 'ORANGE' | 'GREY' |'VIOLET' | 'PINK' | 'BRAND';
SHADE           :   ('DARK' | 'LIGHT')'-'[1-6];
TYPE            :   'BUTTON'|'TEXT';
ALIGN           :   'CENTER'|'START'|'END';
MEDIA           :   'PHONE' | 'COMPUTER' | 'TABLET';
FONTFAM         :   'SERIF' | 'SANS-SERIF' | 'SCRIPT' | 'DISPLAY';
STRING          :   '\''~('\n'|'\r')*?'\'';
WORD            :   LETTER+;

/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
