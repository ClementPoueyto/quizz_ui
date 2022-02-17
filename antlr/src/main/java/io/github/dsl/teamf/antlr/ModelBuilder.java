package io.github.dsl.teamf.antlr;

import io.github.dsl.teamf.antlr.grammar.*;
import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.ButtonComponent;
import io.github.dsl.teamf.kernel.behavioral.TextAlign;
import io.github.dsl.teamf.kernel.behavioral.TextComponent;
//import io.github.dsl.teamf.kernel.structural.quizz.Question;
//import io.github.dsl.teamf.kernel.structural.quizz.Statement;
import io.github.dsl.teamf.kernel.behavioral.UiComponent;
import io.github.dsl.teamf.kernel.structural.quizz.Answer;
import io.github.dsl.teamf.kernel.structural.quizz.Question;
import io.github.dsl.teamf.kernel.structural.quizz.QuizElement;
import io.github.dsl.teamf.kernel.structural.quizz.Statement;
import io.github.dsl.teamf.kernel.structural.ui.Grid;
import io.github.dsl.teamf.kernel.structural.ui.Size;
import io.github.dsl.teamf.kernel.structural.ui.Zone;


import java.awt.*;
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


    private Map<String, List<Binding>>  bindings  = new HashMap<>();
    private Grid grid = null;
    private Map<String, Zone>   zones= new HashMap<>();
    private List<Size> rows= new ArrayList<>();
    private List<Size> cols= new ArrayList<>();
    private Question ques;
    private Statement statement;
    private Answer answer;
    private Zone zone;
    private List<QuizElement> quizElement = new ArrayList<>();
    private List<UiComponent> componentList=new ArrayList<>();

    private class Binding { // used to support state resolution for transitions
        String to; // name of the next state, as its instance might not have been compiled yet

    }


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

    @Override
    public void enterGap(QuizzParser.GapContext ctx) {
        grid.setGap(Size.valueOf(ctx.value.getText().toLowerCase()));
    }

    @Override
    public void enterZone(QuizzParser.ZoneContext ctx) {
        zone = new Zone();
        zone.setColor(ctx.color.getText().toLowerCase());
        zone.setName(ctx.name.getText());
        zone.setStart(new int[]{Integer.parseInt(ctx.column_start.getText()),Integer.parseInt(ctx.row_start.getText())});
        zone.setEnd(new int[]{Integer.parseInt(ctx.column_end.getText()),Integer.parseInt(ctx.row_end.getText())});

    }
    @Override public void exitZone(QuizzParser.ZoneContext ctx) {
        zone.setQuizElement(ques);
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

    @Override public void exitGrid(QuizzParser.GridContext ctx) {
    }

    @Override public void enterQuestion(QuizzParser.QuestionContext ctx) {
         ques=new Question();
    }


    @Override public void enterStatement(QuizzParser.StatementContext ctx) {
        statement= new Statement();
    }

    @Override public void enterText(QuizzParser.TextContext ctx) {
        TextComponent textComponent= new TextComponent();
        textComponent.setColor(ctx.color.getText().toLowerCase());
        textComponent.setTextAlign(TextAlign.valueOf(ctx.textAlign.getText().toLowerCase()));
        textComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        statement.setStatement(textComponent);
        ques.setStatement(statement);
        componentList.add(textComponent);
    }

    @Override public void enterAnswer(QuizzParser.AnswerContext ctx) {
        answer=new Answer();
    }
    @Override public void enterButton(QuizzParser.ButtonContext ctx) {
        ButtonComponent buttonComponent= new ButtonComponent();
        buttonComponent.setColor(ctx.color.getText().toLowerCase());
        buttonComponent.setMargin(Size.valueOf(ctx.margin.getText().toLowerCase()));
        buttonComponent.setSize(Size.valueOf(ctx.size.getText().toLowerCase()));
        answer.setAnswer(buttonComponent);
        ques.setAnswer(answer);
        componentList.add(buttonComponent);
    }

    @Override public void exitQuestion(QuizzParser.QuestionContext ctx) {
        quizElement.add(ques);
    }




}

