package io.github.dsl.teamf.antlr;

import java.util.ArrayList;
import java.util.List;

import io.github.dsl.teamf.antlr.grammar.*;
import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.Theme;
import io.github.dsl.teamf.kernel.structural.GridLayout;
import io.github.dsl.teamf.kernel.structural.Layout;
import io.github.dsl.teamf.kernel.structural.Size;

public class ModelBuilder extends QuizzBaseListener {

    /********************
     ** Business Logic **
     ********************/

    private App app = null;
    private boolean built = false;

    public App retrieve() {
        if (built) { return app; }
        throw new RuntimeException("Cannot retrieve a model that was not created!");
    }

    public String unquote(String quotedString) {
        return quotedString.substring(1, quotedString.length()-1);
    }

    /*******************
     ** Symbol tables **
     *******************/

    GridLayout grid;
    List<Layout> row;

    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(QuizzParser.RootContext ctx) {
        built = false;
        app = new App();
    }

    @Override public void exitRoot(QuizzParser.RootContext ctx) {
        // Resolving states in transitions

        this.built = true;
    }

    @Override
    public void enterAppDeclaration(QuizzParser.AppDeclarationContext ctx) {
        app.setName(ctx.name.getText());
        app.setQuizPath(unquote(ctx.quizPath.getText()));
    }

    @Override
    public void enterLayoutAttribute(QuizzParser.LayoutAttributeContext ctx) {
        app.setLayout(new Layout(ctx.layoutName.getText()));
    }

    @Override
    public void enterThemeAttribute(QuizzParser.ThemeAttributeContext ctx) {
        app.setTheme(new Theme(ctx.themeName.getText()));
    }

    @Override
    public void enterThemeDeclaration(QuizzParser.ThemeDeclarationContext ctx) {
        Theme appTheme = app.getTheme();
        appTheme.setPrimaryColor(ctx.primary.getText());
        appTheme.setSecondaryColor(ctx.secondary.getText());
        appTheme.setFont(ctx.fontFamily.getText());
    }

    @Override
    public void enterGridDeclaration(QuizzParser.GridDeclarationContext ctx) {
        grid = new GridLayout(ctx.gridName.getText());
        grid.setGap(Size.valueOf(ctx.gap.getText()));
    }

    @Override
    public void enterColumn(QuizzParser.ColumnContext ctx) {
        int currentNumberOfColumns = grid.getSizeByColumnIndex().keySet().size();
        grid.getSizeByColumnIndex().put(currentNumberOfColumns, Size.valueOf(ctx.columnSize.getText()));
    }

    @Override
    public void enterRow(QuizzParser.RowContext ctx) {
        row = new ArrayList<>();
        int currentNumberOfLines = grid.getSizeByRowIndex().keySet().size();
        grid.getSizeByRowIndex().put(currentNumberOfLines, Size.valueOf(ctx.rowSize.getText()));
    }

    @Override
    public void enterZone(QuizzParser.ZoneContext ctx) {
        row.add(new Layout(ctx.zoneName.getText()));
    }

    @Override
    public void exitRow(QuizzParser.RowContext ctx) {
        int currentNumberOfLines = grid.getLayouts().length;
        Layout rows[][] = new Layout[currentNumberOfLines + 1][];
        System.out.println(currentNumberOfLines);
        for(int i = 0; i < currentNumberOfLines; i++)
            rows[i] = grid.getLayouts()[i];
        rows[currentNumberOfLines] = row.toArray(new Layout[0]);
        grid.setLayouts(rows);
    }

    @Override
    public void exitGridDeclaration(QuizzParser.GridDeclarationContext ctx) {
        app.setLayout(grid);
    }
}

