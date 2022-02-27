package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitor;

public class RadioButtonComponent extends UIComponent {
    private String[] options;
    private boolean bindedToAnswers = false;

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public boolean isBindedToAnswers() {
        return bindedToAnswers;
    }

    public void setBindedToAnswers(boolean bindedToAnswers) {
        this.bindedToAnswers = bindedToAnswers;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
