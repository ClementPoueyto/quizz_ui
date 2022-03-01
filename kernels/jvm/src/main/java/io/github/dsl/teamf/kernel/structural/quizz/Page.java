package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.Navigation;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Page extends QuizElement {
    Question question;
    private Navigation navigation;
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Navigation getNavigation() {
        return navigation;
    }

    public void setNavigation(Navigation navigation) {
        this.navigation = navigation;
    }
}
