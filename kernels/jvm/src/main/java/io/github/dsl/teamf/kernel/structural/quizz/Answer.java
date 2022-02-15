package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.ButtonComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Answer extends QuestionElement{

    private ButtonComponent answer;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public ButtonComponent getAnswer() {
        return answer;
    }

    public void setAnswer(ButtonComponent answer) {
        this.answer = answer;
    }
}
