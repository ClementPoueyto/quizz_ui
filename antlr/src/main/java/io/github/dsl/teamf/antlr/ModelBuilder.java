package io.github.dsl.teamf.antlr;

import io.github.dsl.teamf.antlr.grammar.*;
import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.*;
//import io.github.dsl.teamf.kernel.structural.quizz.Question;
//import io.github.dsl.teamf.kernel.structural.quizz.Statement;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.structural.quizz.*;
import io.github.dsl.teamf.kernel.structural.quizz.Timer;
import io.github.dsl.teamf.kernel.structural.ui.*;


import java.util.*;

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
    private List<Layout> layouts = new ArrayList<>();
    private Map<String, Zone>   zones= new HashMap<>();
    private Question ques;
    private Statement statement;
    private SingleAnswer singleAnswer;
    private MultipleAnswer multipleAnswer;
    private OpenAnswer openAnswer;
    private QuizInfo quizInfo;
    private Timer timer;
    private Theme theme;
    private ProgressBar progressBar;

    private Zone currentZone;

    private List<QuizElement> quizElements = new ArrayList<>();
    private List<UiComponent> componentList=new ArrayList<>();

    private TextComponent textComponent;
    private ButtonComponent buttonComponent;
    private CheckBoxComponent checkBoxComponent;
    private ClockComponent clockComponent;
    private TextInputComponent textInputComponent;
    private MeterComponent meterComponent;

    private QuizElement currentQuizElement;
    private Layout currenLayout;
    private List<Size> currentRows = new ArrayList<>();
    private List<Size> currentColumn = new ArrayList<>();
    private int countLineArrangement;
    private List<List<Zone>> arrangement =null; 

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
        grid.setLayouts(layouts);
        this.theApp.setGrid(grid);
    }

    @Override
    public void enterLayout(QuizzParser.LayoutContext ctx){
        currenLayout = new Layout();
        currentColumn = new ArrayList<>();
        currentRows = new ArrayList<>();
    }

    @Override
    public void exitLayout(QuizzParser.LayoutContext ctx){
        currenLayout.setColumns(currentColumn);
        currenLayout.setRows(currentRows);
        currenLayout.setArrangement(arrangement);
        grid.getLayouts().add(currenLayout);
    }

    @Override
    public void enterArrangement(QuizzParser.ArrangementContext ctx){
        countLineArrangement = -1;
        arrangement = new ArrayList<>();
    }

    @Override
    public void enterLine(QuizzParser.LineContext ctx){
        countLineArrangement++;
        arrangement.add(new ArrayList<>());
    }

    @Override
    public void enterZone_name(QuizzParser.Zone_nameContext ctx){
        arrangement.get(countLineArrangement).add(zones.get(ctx.IDENTIFIER().getText()));
    }

    @Override public void exitGrid(QuizzParser.GridContext ctx) {
        this.theApp.setQuizElementList(quizElements);
    }

    @Override
    public void enterGap(QuizzParser.GapContext ctx) {
       currenLayout.setGap(Size.valueOf(ctx.value.getText().toLowerCase()));
    }

    @Override
    public void enterScreen_condition(QuizzParser.Screen_conditionContext ctx){
        ScreenSize screenSize =ScreenSize.valueOf(ctx.media.getText());
        ScreenCondition screenCondition = new ScreenCondition();
        screenCondition.setScreenSize(screenSize);
        currenLayout.setScreenCondition(screenCondition);
    }

    @Override
    public void enterZone(QuizzParser.ZoneContext ctx) {
        currentQuizElement = null;
        Zone zone = new Zone();
        if(ctx.color==null)
            System.out.printf("IT's NULL");
        else
        System.out.printf("COLOR"+ctx.color.getText());
        if(ctx.color!=null)
        zone.setColor(ctx.color.getText().toLowerCase());
        else
            if (theApp.getTheme()!=null)
                zone.setColor(theApp.getTheme().getPrimaryColor());
        if(ctx.alignement!=null){
            zone.setAlignement(TextAlign.valueOf(ctx.alignement.getText().toLowerCase()));
        }
        zone.setName(ctx.name.getText());
        currentZone = zone;
    }
    @Override public void exitZone(QuizzParser.ZoneContext ctx) {
        if(currentQuizElement!=null){
            currentZone.setQuizElement(currentQuizElement);
        }
        this.grid.getZones().add(currentZone);
        zones.put(ctx.name.getText(),currentZone);
    }

    @Override
    public void enterRow(QuizzParser.RowContext ctx) {
        currentRows.add(Integer.parseInt(ctx.value.getText()),Size.valueOf(ctx.size.getText().toLowerCase()));
    }

    @Override
    public void enterColumn(QuizzParser.ColumnContext ctx) {
        currentColumn.add(Integer.parseInt(ctx.value.getText()),Size.valueOf(ctx.size.getText().toLowerCase()));
    }

    @Override
    public void exitRows(QuizzParser.RowsContext ctx) {
        //grid.setRows(this.rows);
        currenLayout.setRows(currentRows);
    }

    @Override
    public void exitColumns(QuizzParser.ColumnsContext ctx) {
        //grid.setColumns(this.cols);
        currenLayout.setColumns(currentColumn);
    }

    @Override public void enterQuestion(QuizzParser.QuestionContext ctx) {
         ques=new Question();
    }

    @Override public void exitQuestion(QuizzParser.QuestionContext ctx) {
        currentQuizElement = ques;
        ques.setStatement(statement);
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



    @Override public void enterSingle_answer(QuizzParser.Single_answerContext ctx) {
        singleAnswer=new SingleAnswer();
    }
    @Override public void exitSingle_answer(QuizzParser.Single_answerContext ctx) {
        singleAnswer.setAnswer(buttonComponent);
        ques.setAnswer(singleAnswer);

    }

    @Override public void enterMultiple_answer(QuizzParser.Multiple_answerContext ctx) {
        multipleAnswer=new MultipleAnswer();
    }
    @Override public void exitMultiple_answer(QuizzParser.Multiple_answerContext ctx) {
        multipleAnswer.setAnswer(checkBoxComponent);
        ques.setAnswer(multipleAnswer);
    }

    @Override public void enterButton(QuizzParser.ButtonContext ctx) {
        ButtonComponent buttonComponent= new ButtonComponent();
        if(ctx.color!=null){
            buttonComponent.setColor(ctx.color.getText().toLowerCase());
        }
        else
            if(theApp.getTheme()!=null)
                buttonComponent.setColor(theApp.getTheme().getSecondaryColor());
        buttonComponent.setMargin(Size.valueOf(ctx.margin.getText().toLowerCase()));
        buttonComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        this.buttonComponent = buttonComponent;
        componentList.add(buttonComponent);
    }

    @Override public void enterCheckboxgroup(QuizzParser.CheckboxgroupContext ctx) {
        CheckBoxComponent checkBoxComponent= new CheckBoxComponent();
        if(ctx.gapanswer!=null){
            checkBoxComponent.setGap(Size.valueOf(ctx.gapanswer.getText().toLowerCase()));
        }

        this.checkBoxComponent = checkBoxComponent;
        componentList.add(checkBoxComponent);
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

    @Override public void enterFont(QuizzParser.FontContext ctx) {
        FontStyle font =new FontStyle();
        font.setFamily(ctx.family.getText());
        if(ctx.size!=null)
           font.setSize(Integer.parseInt(ctx.size.getText()));
        theme.setFontStyle(font);
    }

    @Override public void enterTheme(QuizzParser.ThemeContext ctx) {
        theme =new Theme();
        theme.setPrimaryColor(ctx.primary.getText());
        theme.setSecondaryColor(ctx.secondary.getText());

    }

    @Override public void exitTheme(QuizzParser.ThemeContext ctx) {
        theApp.setTheme(theme);
    }

    @Override public void enterProgressbar(QuizzParser.ProgressbarContext ctx){
        progressBar= new ProgressBar();
    }
    @Override public void exitProgressbar(QuizzParser.ProgressbarContext ctx){
        progressBar.setMeter(meterComponent);
        this.currentQuizElement = this.progressBar;
        this.quizElements.add(progressBar);
    }

    @Override public void enterMeter(QuizzParser.MeterContext ctx) {
        meterComponent = new MeterComponent();
        if (ctx.color != null) {
            meterComponent.setColor(ctx.color.getText());
        }
        if (ctx.size != null) {
            meterComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        }
        if (ctx.type != null) {
            meterComponent.setType(MeterType.valueOf(ctx.type.getText().toLowerCase()));
        }
        if (ctx.thickness != null) {
            meterComponent.setThickness(Size.valueOf(ctx.thickness.getText().toLowerCase()));
        }

    }

    @Override
    public void enterTextInput(QuizzParser.TextInputContext ctx) {
        TextInputComponent textInputComponent = new TextInputComponent();
        if (ctx.textAlign != null)
            textInputComponent.setTextAlign(TextAlign.valueOf(ctx.textAlign.getText().toLowerCase()));
        if (ctx.placeholder != null) {
            String placeHolder = ctx.placeholder.getText();
            placeHolder = placeHolder.substring(1);
            placeHolder = placeHolder.substring(0, placeHolder.length() - 1);
            textInputComponent.setPlaceholder(placeHolder);
        }

        textInputComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        this.textInputComponent = textInputComponent;
        this.componentList.add(textInputComponent);
    }

    @Override
    public void enterOpen_answer(QuizzParser.Open_answerContext ctx) {
        openAnswer = new OpenAnswer();
    }

    @Override
    public void exitOpen_answer(QuizzParser.Open_answerContext ctx) {
        openAnswer.setAnswer(textInputComponent);
        ques.setAnswer(openAnswer);
    }

}

