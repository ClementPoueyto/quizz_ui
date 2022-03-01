package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;
import io.github.dsl.teamf.kernel.structural.quizz.QuizElement;

public class Send extends QuizElement {
    ButtonComponent send;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public ButtonComponent getSend() {
        return send;
    }

    public void setSend(ButtonComponent send) {
        send.setFunctionName("onQuizEnd(this.state.quiz)");
        this.send = send;
    }
}
