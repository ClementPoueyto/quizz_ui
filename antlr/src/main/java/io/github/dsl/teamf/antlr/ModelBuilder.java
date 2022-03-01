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
    private SingleAnswer singleAnswer;
    private MultipleAnswer multipleAnswer;
    private OpenAnswer openAnswer;
    private PictureStatement pictureStatement;
    private TextStatement textStatement;
    private QuizInfo quizInfo;
    private Timer timer;
    private Theme theme;
    private ProgressBar progressBar;

    private Zone currentZone;
    private Border border;
    private Navigation navigation;

    private List<QuizElement> quizElements = new ArrayList<>();
    private List<UiComponent> componentList=new ArrayList<>();
    private List<Answer> answers=new ArrayList<>();
    private List<Statement> statements=new ArrayList<>();

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
    private Page page = new Page();

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
        currenLayout.setArrangement(arrangement);
        currenLayout.setColumns(currentColumn);
        currenLayout.setRows(currentRows);
        grid.getLayouts().add(currenLayout);

    }

    @Override
    public void enterArrangement(QuizzParser.ArrangementContext ctx){
        countLineArrangement = -1;
        arrangement = new ArrayList<>();
        for(int i=0;i<ctx.line().size();i++){
            currentRows.add(i,Size.auto);
        }
        for (int j = 0; j < ctx.line().get(0).zone_name().size(); j++) {
                currentColumn.add(j, Size.auto);
        }
    }

    @Override
    public void enterLine(QuizzParser.LineContext ctx){
        countLineArrangement++;
        int index = ((QuizzParser.ArrangementContext)ctx.parent).line().indexOf(ctx);
        if(ctx.row!=null)
            currentRows.set(index,Size.valueOf(ctx.row.getText().toLowerCase()));
        arrangement.add(new ArrayList<>());
    }

    @Override
    public void enterZone_name(QuizzParser.Zone_nameContext ctx){
        if(zones.get(ctx.IDENTIFIER().getText()) == null){
            try {
                throw new NamedElementNotFoundException(ctx.IDENTIFIER().getText());
            } catch (NamedElementNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
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
        border = null;
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
            zone.setAlignement(Align.valueOf(ctx.alignement.getText().toLowerCase()));
        }
        zone.setName(ctx.name.getText());
        if(ctx.rounding !=null){
            zone.setRounding(Size.valueOf(ctx.rounding.getText().toLowerCase()));
        }   
        currentZone = zone;
    }
     
    @Override public void enterBorder(QuizzParser.BorderContext ctx){
        border = new Border();
        if(ctx.color != null){
            border.setColor(ctx.color.getText());
        }
        else
            if(theApp.getTheme()!=null&&theApp.getTheme().getSecondaryColor()!=null)
                border.setColor(theApp.getTheme().getSecondaryColor());
        if(ctx.size != null){
            border.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        }
        if(ctx.style != null) {
            border.setStyle(ctx.style.getText().toLowerCase());
        }
    }

    @Override public void exitZone(QuizzParser.ZoneContext ctx) {
        if(currentQuizElement!=null){
            currentZone.setQuizElement(currentQuizElement);
        }
        if(border != null){
            currentZone.setBorder(border);
        }
        this.grid.getZones().add(currentZone);
        zones.put(ctx.name.getText(),currentZone);
    }

    @Override
    public void enterColumn(QuizzParser.ColumnContext ctx) {
        int index = ((QuizzParser.ColumnsContext)ctx.parent).column().indexOf(ctx);
        System.out.println(ctx.SIZE().getText());
        currentColumn.set(index,Size.valueOf(ctx.SIZE().getText().toLowerCase()));
    }

    @Override
    public void enterPage(QuizzParser.PageContext ctx) {
        page = new Page();
        this.currentQuizElement = page;
        if(navigation != null)
            page.setNavigation(navigation);
        quizElements.add(page);
    }
    @Override
    public void enterSend(QuizzParser.SendContext ctx) {
        this.componentList.clear();

    }
    @Override
    public void exitSend(QuizzParser.SendContext ctx) {
        Send send = new Send();
        if(ctx.button() != null)
            send.setSend((ButtonComponent) this.componentList.get(0));
        this.componentList.clear();
        this.currentQuizElement = send;
    }
    @Override
    public void exitPage(QuizzParser.PageContext ctx) {
        Page page = (Page)this.currentQuizElement;
        page.setQuestion(ques);
    }
    @Override public void enterNavigable(QuizzParser.NavigableContext ctx) {
        this.componentList.clear();
    }
    @Override public void exitNavigable(QuizzParser.NavigableContext ctx) {
        navigation = new Navigation();
        this.currentQuizElement = navigation;
        if(page != null)
            page.setNavigation(navigation);

        if(ctx.button() != null){
            navigation.setSuivant((ButtonComponent) this.componentList.get(0));
        }
        if(ctx.backward() != null) {
            navigation.setPrecedent((ButtonComponent) this.componentList.get(1));
        }
        this.componentList.clear();
    }



    @Override public void enterQuestion(QuizzParser.QuestionContext ctx) {
         ques=new Question();
    }
    @Override public void exitQuestion(QuizzParser.QuestionContext ctx) {
        ques.setStatements(statements);
        ques.setAnswers(answers);
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

    @Override public void enterText(QuizzParser.TextContext ctx) {
        textComponent= new TextComponent();
        if(ctx.color!=null){
            textComponent.setColor(ctx.color.getText().toLowerCase());
        }
        else
            if(theApp.getTheme()!=null&&theApp.getTheme().getSecondaryColor()!=null)
                textComponent.setColor(theApp.getTheme().getSecondaryColor());
        if(ctx.textAlign!=null){
            textComponent.setTextAlign(Align.valueOf(ctx.textAlign.getText().toLowerCase()));
        }
        textComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        this.componentList.add(textComponent);


    }


    @Override public void enterSingle_answer(QuizzParser.Single_answerContext ctx) {
        singleAnswer=new SingleAnswer();
    }
    @Override public void exitSingle_answer(QuizzParser.Single_answerContext ctx) {
        singleAnswer.setAnswer(buttonComponent);
        answers.add(singleAnswer);

    }

    @Override public void enterMultiple_answer(QuizzParser.Multiple_answerContext ctx) {
        multipleAnswer=new MultipleAnswer();
    }
    @Override public void exitMultiple_answer(QuizzParser.Multiple_answerContext ctx) {
        multipleAnswer.setAnswer(checkBoxComponent);
        answers.add(multipleAnswer);
    }

    @Override public void enterButton(QuizzParser.ButtonContext ctx) {
        ButtonComponent buttonComponent= new ButtonComponent();
        if(ctx.color!=null){
            buttonComponent.setColor(ctx.color.getText().toLowerCase());
        }
        else{
            if(theApp.getTheme()!=null&&theApp.getTheme().getSecondaryColor()!=null)
                buttonComponent.setColor(theApp.getTheme().getSecondaryColor());
        }
        if(ctx.margin!=null){
            buttonComponent.setMargin(Size.valueOf(ctx.margin.getText().toLowerCase()));
        }
        if(ctx.size!=null){
            buttonComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        }
        if(ctx.labelValue!=null){
            buttonComponent.setVariableName(ctx.labelValue.getText());
        }
        if(ctx.buttonAlign!=null){
            buttonComponent.setAlign(Align.valueOf(ctx.buttonAlign.getText().toLowerCase()));
        }
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
            clock.setSelfAlign(Align.valueOf(ctx.textAlign.getText().toLowerCase()));
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
        if(ctx.primary!=null){
            theme.setPrimaryColor(ctx.primary.getText());
        }
        if(ctx.secondary!=null) {
            theme.setSecondaryColor(ctx.secondary.getText());
        }

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
        else
            if(theApp.getTheme()!=null&&theApp.getTheme().getSecondaryColor()!=null)
                meterComponent.setColor(theApp.getTheme().getSecondaryColor());
        if (ctx.background != null) {
            meterComponent.setBackgroundColor(ctx.background.getText());
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
    @Override public void enterText_statement(QuizzParser.Text_statementContext ctx) {
        textStatement= new TextStatement();

    }

    @Override public void exitText_statement(QuizzParser.Text_statementContext ctx) {
        textStatement.setStatement(textComponent);
        statements.add(textStatement);
    }

    @Override public void enterPicture_statement(QuizzParser.Picture_statementContext ctx) {
        pictureStatement=new PictureStatement();
    }

    @Override public void exitPicture_statement(QuizzParser.Picture_statementContext ctx) {
        statements.add(pictureStatement);
    }

    @Override public void enterPicture(QuizzParser.PictureContext ctx) {
        PictureComponent picture=new PictureComponent();

        picture.setHeight(Size.valueOf(ctx.height.getText().toLowerCase()));
        picture.setWidth(Size.valueOf(ctx.width.getText().toLowerCase()));
       // picture.setPath(ctx.path.getText());
        pictureStatement.setPicture(picture);
        componentList.add(picture);
    }


    @Override
    public void enterTextInput(QuizzParser.TextInputContext ctx) {
        TextInputComponent textInputComponent = new TextInputComponent();
        if (ctx.textAlign != null)
            textInputComponent.setTextAlign(Align.valueOf(ctx.textAlign.getText().toLowerCase()));
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
        answers.add(openAnswer);
    }

}

