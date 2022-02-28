package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.quizz.QuestionElement;
import io.github.dsl.teamf.kernel.structural.ui.Size;

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
        this.precedent = precedent;
    }

    public ButtonComponent getSuivant() {
        return suivant;
    }

    public void setSuivant(ButtonComponent suivant) {
        this.suivant = suivant;
    }
}
