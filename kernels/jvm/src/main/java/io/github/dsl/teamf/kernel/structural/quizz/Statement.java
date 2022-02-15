package io.github.dsl.teamf.kernel.structural.quizz;

import io.github.dsl.teamf.kernel.behavioral.TextComponent;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Statement extends QuestionElement{
    private TextComponent statement;

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public TextComponent getStatement() {
        return statement;
    }

    public void setStatement(TextComponent statement) {
        this.statement.setVariableName("statement");
        this.statement = statement;
    }
}
