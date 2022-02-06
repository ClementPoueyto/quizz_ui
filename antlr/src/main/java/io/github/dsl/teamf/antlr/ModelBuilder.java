package io.github.dsl.teamf.antlr;

import io.github.dsl.teamf.antlr.grammar.ArduinomlBaseListener;
import io.github.dsl.teamf.antlr.grammar.ArduinomlParser;
import io.github.dsl.teamf.kernel.App;


import java.util.*;

public class ModelBuilder extends ArduinomlBaseListener {

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



    private class Binding { // used to support state resolution for transitions
        String to; // name of the next state, as its instance might not have been compiled yet

    }


    /**************************
     ** Listening mechanisms **
     **************************/

    @Override
    public void enterRoot(ArduinomlParser.RootContext ctx) {
        built = false;
        theApp = new App();
    }

    @Override public void exitRoot(ArduinomlParser.RootContext ctx) {
        // Resolving states in transitions

        this.built = true;
    }

    @Override
    public void enterDeclaration(ArduinomlParser.DeclarationContext ctx) {
        theApp.setName(ctx.name.getText());
    }


}

