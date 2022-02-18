package io.github.dsl.teamf.antlr;

import io.github.dsl.teamf.antlr.grammar.*;
import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
//import io.github.dsl.teamf.kernel.structural.quizz.Question;
//import io.github.dsl.teamf.kernel.structural.quizz.Statement;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.structural.quizz.*;
import io.github.dsl.teamf.kernel.structural.quizz.Timer;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Zone;


import java.util.*;
import java.util.List;

public class ModelBuilder extends QuizzBaseListener {

    /********************
     ** Business Logic **
     ********************/

    private App theApp = null;
    private boolean built = false;

    public App retrieve() {
        if (built) { return theApp; }
        throw new RuntimeException("Cannot retrieve a model that was not created!");
    }

    /*******************
     ** Symbol tables **
     *******************/


    private Grid grid = null;
    private Map<String, Zone>   zones= new HashMap<>();
    private List<Size> rows= new ArrayList<>();
    private List<Size> cols= new ArrayList<>();
    private Question ques;
    private Statement statement;
    private Answer answer;
    private QuizInfo quizInfo;
    private Timer timer;

    private Zone zone;
    private List<QuizElement> quizElements = new ArrayList<>();
    private List<UiComponent> componentList=new ArrayList<>();

    private TextComponent textComponent;
    private ButtonComponent buttonComponent;
    private ClockComponent clockComponent;

    private QuizElement currentQuizElement;

    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(QuizzParser.RootContext ctx) {
        built = false;
        theApp = new App();
    }

    @Override public void exitRoot(QuizzParser.RootContext ctx) {
        // Resolving states in transitions

        this.built = true;
    }

    @Override
    public void enterDeclaration(QuizzParser.DeclarationContext ctx) {
        theApp.setName(ctx.name.getText());
    }

    @Override
    public void enterGrid(QuizzParser.GridContext ctx) {

        grid = new Grid();
        this.theApp.setGrid(grid);
    }

    @Override public void exitGrid(QuizzParser.GridContext ctx) {
        this.theApp.setQuizElementList(quizElements);
    }

    @Override
    public void enterGap(QuizzParser.GapContext ctx) {
        grid.setGap(Size.valueOf(ctx.value.getText().toLowerCase()));
    }

    @Override
    public void enterZone(QuizzParser.ZoneContext ctx) {
        currentQuizElement = null;
        zone = new Zone();
        zone.setColor(ctx.color.getText().toLowerCase());
        zone.setName(ctx.name.getText());
        zone.setStart(new int[]{Integer.parseInt(ctx.column_start.getText()),Integer.parseInt(ctx.row_start.getText())});
        zone.setEnd(new int[]{Integer.parseInt(ctx.column_end.getText()),Integer.parseInt(ctx.row_end.getText())});

    }
    @Override public void exitZone(QuizzParser.ZoneContext ctx) {
        if(currentQuizElement!=null){
            zone.setQuizElement(currentQuizElement);
        }
        this.grid.getZones().add(zone);
    }

    @Override
    public void enterRow(QuizzParser.RowContext ctx) {
        rows.add(Integer.parseInt(ctx.value.getText()),Size.valueOf(ctx.size.getText().toLowerCase()));
    }

    @Override
    public void enterColumn(QuizzParser.ColumnContext ctx) {
        cols.add(Integer.parseInt(ctx.value.getText()),Size.valueOf(ctx.size.getText().toLowerCase()));
    }

    @Override
    public void exitRows(QuizzParser.RowsContext ctx) {
        grid.setRows(this.rows);
    }

    @Override
    public void exitColumns(QuizzParser.ColumnsContext ctx) {
        grid.setColumns(this.cols);
    }

    @Override public void enterQuestion(QuizzParser.QuestionContext ctx) {
         ques=new Question();
    }
    @Override public void exitQuestion(QuizzParser.QuestionContext ctx) {
        currentQuizElement = ques;
        ques.setStatement(statement);
        ques.setAnswer(answer);
        quizElements.add(ques);
    }

    @Override
    public void enterQuiz_info(QuizzParser.Quiz_infoContext ctx) {
        this.quizInfo = new QuizInfo();
    }
    @Override
    public void exitQuiz_info(QuizzParser.Quiz_infoContext ctx) {
        if(ctx.description!=null){
            this.quizInfo.setTitle((TextComponent) this.componentList.get(this.componentList.size()-2));
            this.quizInfo.setTheme((TextComponent) this.componentList.get(this.componentList.size()-1));
        }
        else{
            this.quizInfo.setTitle((TextComponent) this.componentList.get(this.componentList.size()-1));
        }
        this.currentQuizElement = this.quizInfo;
        this.quizElements.add(quizInfo);

    }

    @Override
    public void enterTimer(QuizzParser.TimerContext ctx){
        this.timer = new Timer();
    }
    @Override
    public void exitTimer(QuizzParser.TimerContext ctx){
        this.timer.setClockComponent(clockComponent);
        this.currentQuizElement = timer;
        this.quizElements.add(timer);

    }

    @Override public void enterStatement(QuizzParser.StatementContext ctx) {
        statement= new Statement();
    }
    @Override public void exitStatement(QuizzParser.StatementContext ctx) {
        statement.setStatement(textComponent);
    }

    @Override public void enterText(QuizzParser.TextContext ctx) {
        TextComponent textComponent= new TextComponent();
        if(ctx.color!=null){
            textComponent.setColor(ctx.color.getText().toLowerCase());
        }
        if(ctx.textAlign!=null){
            textComponent.setTextAlign(TextAlign.valueOf(ctx.textAlign.getText().toLowerCase()));
        }
        textComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        this.textComponent = textComponent;
        this.componentList.add(textComponent);

    }

    @Override public void enterAnswer(QuizzParser.AnswerContext ctx) {
        answer=new Answer();
    }
    @Override public void exitAnswer(QuizzParser.AnswerContext ctx) {
        answer.setAnswer(buttonComponent);
    }
    @Override public void enterButton(QuizzParser.ButtonContext ctx) {
        ButtonComponent buttonComponent= new ButtonComponent();
        if(ctx.color!=null){
            buttonComponent.setColor(ctx.color.getText().toLowerCase());
        }
        buttonComponent.setMargin(Size.valueOf(ctx.margin.getText().toLowerCase()));
        buttonComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        this.buttonComponent = buttonComponent;
        componentList.add(buttonComponent);
    }

    @Override public void enterClock(QuizzParser.ClockContext ctx) {
        ClockComponent clock = new ClockComponent();
        clock.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        if(ctx.textAlign!=null){
            clock.setSelfAlign(TextAlign.valueOf(ctx.textAlign.getText().toLowerCase()));
        }
        if(ctx.chrono!=null&&ctx.chrono.getText().equals("countdown")){
            clock.setClockDirection(ClockDirection.backward);
        }
        else{
            clock.setClockDirection(ClockDirection.forward);
        }
        if(ctx.type!=null){
            clock.setType(ClockType.valueOf(ctx.type.getText().toLowerCase()));
        }
        if(ctx.startTime!=null){
            clock.setStartChrono(ctx.startTime.getText());
        }
        else{
            clock.setStartChrono(clock.getClockDirection()==ClockDirection.backward?"00:01:00":"00:00:00");
        }
        this.componentList.add(clock);
        this.clockComponent = clock;
    }




}

