package io.github.dsl.teamf.antlr;

import io.github.dsl.teamf.antlr.grammar.*;
import io.github.dsl.teamf.kernel.App;
import io.github.dsl.teamf.kernel.behavioral.Theme;
import io.github.dsl.teamf.kernel.structural.Layout;

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
}

