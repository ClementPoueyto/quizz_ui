grammar Quizz;


/******************
 ** Parser rules **
 ******************/

root            :   declaration theme? grid EOF;

declaration     :   'application' name=IDENTIFIER;

theme           :   'theme' ':' ('primary color' ':')? primary=(COLOR|HEX|SHADE) ('secondary color' ':')? secondary=(COLOR|HEX|SHADE) ('font' ':')? font;
font            :   '{' 'family' ':' family=FONTFAM ('size' ':' size=NUMBER 'px')? '}';

grid            :   'grid' ':' '{'zone+ layout+ '}';
    zone        :   'zone' ':' name=IDENTIFIER ('alignment' alignement=ALIGN)? ('background' color=(COLOR|HEX|SHADE))? ('element' ':' quizz_element)?;
    
layout          :   'layout' ':' '{' screen_condition? gap? rows columns arrangement '}';
    screen_condition : 'when screen is ' media=MEDIA; 
    gap         :   'gap' value=SIZE;
    rows        :   row+;
    row         :   'row' value=NUMBER 'size' size=SIZE;
    columns     :   column+;
    column      :   'col' value=NUMBER 'size' size=SIZE;

arrangement     :   'arrangement' '{' line+ '}';
    line        :   zone_name+ ',';
    zone_name   :   IDENTIFIER ;

quizz_element   : question | quiz_info | timer;
    quiz_info   : ('title' ':' title=text) ('description' ':' description=text)?;
    question    : statement+ 'answer' ':' answer+;
    statement   : 'statement' ':' text;
    answer      : single_answer | multiple_answer;
    single_answer:  'single' button;
    multiple_answer : 'multiple' checkboxgroup;
    timer       : 'timer' ':' clock;

uiElement : button | text | clock | checkboxgroup ;
    checkboxgroup : ('gap' gapanswer=SIZE)?;
    text :  'size' size=SIZE  ('align' textAlign=ALIGN)? ('color' ':' color=(COLOR|HEX|SHADE))?;
    button : ('color' ':' color=(COLOR|HEX|SHADE))? 'size' ':' size=SIZE ('margin' ':' margin=SIZE)?;
    clock :  (chrono=('chrono'|'countdown'))? 'size' size=SIZE ('align' textAlign=ALIGN)? ('start' startTime=TIME)? ('type' type=('DIGITAL'|'ANALOG'))?;
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
TIME            : [0-2][0-3]':'[0-5][0-9]':'[0-5][0-9];
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE)+;
SIZE            :   'XXSMALL' | 'XSMALL' | 'SMALL' | 'MEDIUM' | 'LARGE' | 'XLARGE' | 'AUTO' | 'FULL';
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
ALIGN           :   'CENTER'|'START'|'END';
MEDIA           :   'PHONE' | 'COMPUTER' | 'TABLET';
FONTFAM         :   'SERIF' | 'SANS-SERIF' | 'SCRIPT' | 'DISPLAY';

/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
