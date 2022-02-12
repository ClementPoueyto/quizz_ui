package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;

public class QuizInfo extends QuizElement{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
