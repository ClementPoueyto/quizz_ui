package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.generator.Visitor;

public class Page extends QuizElement {
    Question question;
    boolean allOnPage = true;

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

    public boolean isAllOnPage() {
        return allOnPage;
    }

    public void setAllOnPage(boolean allOnPage) {
        this.allOnPage = allOnPage;
    }
}
