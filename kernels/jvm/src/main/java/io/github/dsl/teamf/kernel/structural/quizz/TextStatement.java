package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class TextStatement extends Statement{
    private TextComponent textStatement;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public TextComponent getTextStatement() {
        return textStatement;
    }

    public void setTextStatement(TextComponent textStatement) {
        textStatement.setVariableName("this.state.quiz.questions[i].statement.text");
        this.textStatement = textStatement;
    }
}
