grammar Quizz;


/******************
 ** Parser rules **
 ******************/

root            :   declaration grid EOF;

declaration     :   'application' name=IDENTIFIER;

grid            :   'grid' ':' '{'zone+ gap rows columns '}';
    zone        :   'zone' ':' name=IDENTIFIER 'background' color=(COLOR|HEX) 'col_start' column_start=NUMBER 'row_start' row_start=NUMBER 'col_end' column_end=NUMBER 'row_end' row_end=NUMBER;
    gap         :   'gap' value=SIZE;
    rows        :   row+;
        row     :   'row' value=NUMBER 'size' size=SIZE;
    columns     :   column+;
        column  :   'col' value=NUMBER 'size' size=SIZE;



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
//TEXT            :   UPPERCASE (IDENTIFIER WS* NEWLINE)+;
//PATH            :   (SPECIAL_CHAR*|LOWERCASE|UPPERCASE)*;
//SPECIAL_CHAR    :   ':'|'\\' |'/' |'.';
LETTER          :   (LOWERCASE|UPPERCASE);
CHAR            :   (NUMBER|LETTER);
HEX             :   '#' CHAR+;
COLOR           :   'RED' | 'BLUE' | 'GREEN' | 'YELLOW' | 'ORANGE' | 'GREY' |'VIOLET' | 'PINK';
/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
