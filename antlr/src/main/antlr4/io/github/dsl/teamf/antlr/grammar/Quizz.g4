grammar Quizz;


/******************
 ** Parser rules **
 ******************/

root            :   declaration grid EOF;

declaration     :   'application' name=IDENTIFIER;

grid            :   'grid' ':' '{'zone+ gap? rows columns '}';
    zone        :   'zone' ':' name=IDENTIFIER 'background' color=(COLOR|HEX|SHADE) 'cols' column_start=NUMBER ':' column_end=NUMBER 'rows' row_start=NUMBER ':' row_end=NUMBER ('element' ':' quizz_element)?;
    gap         :   'gap' value=SIZE;
    rows        :   row+;
        row     :   'row' value=NUMBER 'size' size=SIZE;
    columns     :   column+;
        column  :   'col' value=NUMBER 'size' size=SIZE;

quizz_element   : question | quiz_info;
    quiz_info: ('title' ':' title=text) ('description' ':' description=text)?;
    question: statement+ 'answer' ':' answer+;
    statement : 'statement' ':' text;
    answer:  button;

uiElement : button | text;
    text :  'size' size=SIZE  ('align' textAlign=ALIGN)? ('color' ':' color=(COLOR|HEX|SHADE))?;
    button : ('color' ':' color=(COLOR|HEX|SHADE))? 'size' ':' size=SIZE ('margin' ':' margin=SIZE)?;


//quizPage        :   quizElement+;
//    quizElement :   (questionBlock   |   quizInfo |   navigationQuestion  |   progress    |   timer) zone;
//    question    :   statement+ answer+;
//    statement   :   QUESTION_UI;
//    answer      :   QUESTION_UI;

/*****************
 ** Lexer rules **
 *****************/
//sPORT_NUMBER     :   [1-9] | '11' | '12';
NUMBER          :   [0-9]+;
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;
SIZE            :   'XXSMALL' | 'XSMALL' | 'SMALL' | 'MEDIUM' | 'LARGE' | 'XLARGE';
//QUESTION_UI     :   IMAGE   |   TEXT;
//IMAGE           :   'image' PATH;
TEXT            :   UPPERCASE (IDENTIFIER WS* NEWLINE)+;
//PATH            :   (SPECIAL_CHAR*|LOWERCASE|UPPERCASE)*;
//SPECIAL_CHAR    :   ':'|'\\' |'/' |'.';
LETTER          :   (LOWERCASE|UPPERCASE);
CHAR            :   (NUMBER|LETTER);
HEX             :   '#' CHAR+;
COLOR           :   'RED' | 'BLUE' | 'GREEN' | 'YELLOW' | 'ORANGE' | 'GREY' |'VIOLET' | 'PINK' | 'BRAND';
SHADE           :   ('DARK' | 'LIGHT')'-'[1-6];
TYPE            :   'BUTTON'|'TEXT';
ALIGN           :   'CENTER'|'LEFT'|'RIGHT';
/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
