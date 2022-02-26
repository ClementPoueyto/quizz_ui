package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class TextStatement extends Statement{
    private TextComponent statement;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public TextComponent getStatement() {
        return statement;
    }

    public void setStatement(TextComponent statement) {
        statement.setVariableName("this.state.quiz.questions[i].statement.text");
        this.statement = statement;
    }
}
