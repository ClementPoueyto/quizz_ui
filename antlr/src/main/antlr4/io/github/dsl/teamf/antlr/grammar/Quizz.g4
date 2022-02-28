grammar Quizz;

/******************
 ** Parser rules **
 ******************/

root            :   declaration* appDeclaration  EOF;

appDeclaration  :   'Application' name=WORD 'from quiz' quizPath=STRING appAttributes;

appAttributes   :   (layoutAttribute themeAttribute) | (layoutAttribute themeAttribute);
layoutAttribute :   'uses layout' layoutName=IDENTIFIER;
themeAttribute  :   'uses theme' themeName=IDENTIFIER;

declaration     :   themeDeclaration | gridDeclaration | boxDeclaration;
themeDeclaration:   themeName=IDENTIFIER 'is theme with primary color' primary=COLOR'secondary color' secondary=COLOR'font family' fontFamily=FONTFAM;
gridDeclaration :   gridName=IDENTIFIER 'is grid with' gap=SIZE 'gap wich follows disposition' disposition;
boxDeclaration  :   boxName=IDENTIFIER 'is' (isFlex='flex')? 'box' ( 'with direction' direction=DIRECTION)? 'that contains' boxContent;

disposition     :   columns? row+;
columns         :   column+;
column          :   columnSize=SIZE;
row             :   rowSize=SIZE zone+;
zone            :   zoneName=IDENTIFIER;

boxContent      :   ((text | textInput | button | checkBox)+ | questions);
text            :   'text' (quizTitleBinding | 'with value' textValue=STRING) ('with font size' fontSize=NUMBER)? globalStyle?;
textInput       :   'text input' ('that contains place holder' textValue=STRING)? ('with font size' fontSize=NUMBER)? globalStyle?;
button          :   'button' ('that call' functionName=STRING)? ('contains value' textValue=STRING)?  globalStyle?;
checkBox        :   'checkboxgroup' ('that call' functionName=STRING)? ('and contains option' option=STRING)? 'with' ('gap' gapanswer=SIZE)? globalStyle?;
globalStyle     :   ',' ('aligned' textAlign=ALIGN)? ;
quizTitleBinding:   'binded to quiz title';

questions       :   'the questions:' navigable?;
navigable       :   'questions are navigable forward' (label)? (backward)?;
backward        :   'and backward' (label)?;
label           :   'with labels' value=STRING;
/*****************
 ** Lexer rules **
 *****************/
 
NUMBER          :   [0-9]+;
TIME            : [0-2][0-3]':'[0-5][0-9]':'[0-5][0-9];
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+;
SIZE            :   'XXSMALL' | 'XSMALL' | 'SMALL' | 'MEDIUM' | 'LARGE' | 'XLARGE' | 'AUTO' | 'FULL';
DIRECTION       :   'ROW' | 'COLUMN' | 'ROW_REVERSE' | 'COLUMN_REVERSE' | 'ROW_RESPONSIVE';
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
