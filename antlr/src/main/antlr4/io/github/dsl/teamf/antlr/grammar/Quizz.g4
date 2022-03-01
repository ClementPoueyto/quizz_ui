grammar Quizz;


/******************
 ** Parser rules **
 ******************/

root            :   declaration theme? grid EOF;

declaration     :   'application' name=IDENTIFIER;

theme           :   'theme' '{' ('primary color' ':' primary=(COLOR|HEX|SHADE) ('secondary color' ':' secondary=(COLOR|HEX|SHADE))?)? ('font' font)? '}';
font            :   '{' 'family' family=FONTFAM ('with' 'size' size=NUMBER 'px')? '}';

grid            :   zone+ layout+;
    zone        :   'zone' ':' name=IDENTIFIER ('alignment' alignement=ALIGN)? ('background' color=(COLOR|HEX|SHADE))? ('rounding' rounding=SIZE)? ('border' 'with' border )? (quizz_element)?;
    border      :   ('size' size=SIZE)? (',')? ('style' style=BORDERSTYLE)? (',')? ('color' color=(COLOR|HEX|SHADE))?;


layout          :   'layout' '{' screen_condition? gap? arrangement '}';
    screen_condition : 'when screen is ' media=MEDIA; 
    gap         :   'gap' value=SIZE;
    columns        :   column+ ',';
    column         :   SIZE;
    
arrangement     :   'arrangement' '{' columns? line+ '}';
    line        :   row=SIZE? zone_name+ ',';
    zone_name   :   IDENTIFIER ;

quizz_element   : page | quiz_info | timer | progressbar | navigable | send;
    quiz_info   : ('title' 'with' title=text) ('description' 'with' description=text)?;
    page        : 'contains questions' question;
    question    : 'statement' 'with' statement+ 'answer' 'with' answer+;
    navigable   :   'navigable forward' ('with' button) (backward)?;
    backward    :   'and backward with' button;
    send        :   'send quiz with' button;
    statement   : (text_statement|picture_statement);
    answer      : single_answer | multiple_answer | open_answer;
    single_answer:  'single choice' 'with' button;
    multiple_answer : 'multiple choice' 'with' checkboxgroup;
    text_statement: 'text' 'with' text;
    picture_statement: 'picture' 'with' picture;
    open_answer : 'open' textInput;
    timer       : 'timer' 'with' clock;
    progressbar : 'progress' 'with' meter;


uiElement : button | text | clock | checkboxgroup | meter | textInput | picture;
    checkboxgroup : ('gap' gapanswer=SIZE)?;
    text :  'size' size=SIZE  (',' 'align' textAlign=ALIGN)? (',' 'color' color=(COLOR|HEX|SHADE))?;
    button : 'size' size=SIZE (',' 'color' color=(COLOR|HEX|SHADE))?  (',' 'margin' margin=SIZE)? (',''align' buttonAlign=ALIGN)? (',''label' labelValue=STRING)? (',''function 'function=STRING)?;
    clock :  (chrono=('chrono'|'countdown'))? (',' 'size' size=SIZE)? (',' 'align' textAlign=ALIGN)? (',' 'start' startTime=TIME)? (',' 'type' type=('DIGITAL'|'ANALOG'))?;
    meter :  'size' size=SIZE (',' 'type' type=('CIRCLE'|'BAR'|'PIE'|'SEMICIRCLE'))? (',' 'thickness' thickness=SIZE)? (',' 'color' color=(COLOR|HEX|SHADE))? (',' 'background' background=(COLOR|HEX|SHADE))?;
    textInput : 'size' size=SIZE (',' 'align' textAlign=ALIGN)? (',' 'placeholder' placeholder=STRING)?;
    picture         : 'height' height=SIZE ',' 'width' width=SIZE ;


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
IDENTIFIER      :   LOWERCASE (LOWERCASE|UPPERCASE|NUMBER)+;
SIZE            :   'XXSMALL' | 'XSMALL' | 'SMALL' | 'MEDIUM' | 'LARGE' | 'XLARGE' | 'AUTO' | 'FULL';
BORDERSTYLE     :   'SOLID' | 'DASHED' | 'DOTTED' | 'DOUBLE' | 'RIDGE';
//QUESTION_UI     :   IMAGE   |   TEXT;
//IMAGE           :   'image' PATH;
TEXT            :   UPPERCASE (IDENTIFIER WS* NEWLINE)+;
//PATH            :   (.*|LOWERCASE|UPPERCASE)+;
//SPECIAL_CHAR    :   ':';

//SPECIAL_CHAR    :   ':'|'\\' |'/' |'.';
LETTER          :   (LOWERCASE|UPPERCASE);
CHAR            :   (NUMBER|LETTER);
HEX             :   '#' CHAR+;
COLOR           :   'WHITE' | 'BLACK' | 'RED' | 'BLUE' | 'GREEN' | 'YELLOW' | 'ORANGE' | 'GREY' |'VIOLET' | 'PINK' | 'BRAND';
SHADE           :   ('DARK' | 'LIGHT')'-'[1-6];
TYPE            :   'BUTTON'|'TEXT';
ALIGN           :   'CENTER'|'START'|'END';
MEDIA           :   'PHONE' | 'COMPUTER' | 'TABLET';
FONTFAM         :   'SERIF' | 'SANS-SERIF' | 'SCRIPT' | 'DISPLAY';
STRING          :   '\''~('\n'|'\r')*?'\'';
/*************
 ** Helpers **
 *************/

fragment LOWERCASE  : [a-z];                                 // abstract rule, does not really exists
fragment UPPERCASE  : [A-Z];
NEWLINE             : ('\r'? '\n' | '\r')+      -> skip;
WS                  : ((' ' | '\t')+)           -> skip;     // who cares about whitespaces?
COMMENT             : '#' ~( '\r' | '\n' )*     -> skip;     // Single line comments, starting with a #
