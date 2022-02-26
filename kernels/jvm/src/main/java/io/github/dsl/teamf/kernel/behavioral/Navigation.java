package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.quizz.QuestionElement;

public class Navigation extends QuestionElement {
    ButtonComponent precedent;
    ButtonComponent suivant;


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public ButtonComponent getPrecedent() {
        return precedent;
    }

    public void setPrecedent(ButtonComponent precedent) {
        precedent.setFunctionName("onPrecedentClick(this.state.quiz,item,index)");
        precedent.setVariableName("this.state.quiz.indexQuestion");
        this.precedent = precedent;
    }

    public ButtonComponent getSuivant() {
        return suivant;
    }

    public void setSuivant(ButtonComponent suivant) {
        this.suivant = suivant;
    }
}
