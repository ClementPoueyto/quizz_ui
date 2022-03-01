package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.TextInputComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class OpenAnswer extends Answer {

    private TextInputComponent answer;

    public  OpenAnswer(){
        this.setAnswer(new TextInputComponent());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public TextInputComponent getAnswer() {
        return answer;
    }

    public void setAnswer(TextInputComponent answer) {
        this.answer = answer;
    }
}
