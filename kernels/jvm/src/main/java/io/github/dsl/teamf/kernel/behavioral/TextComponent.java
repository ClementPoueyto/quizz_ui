package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class TextComponent extends UIComponent  {
    private String value;
    private int fontSize;

    public TextComponent(String value) {
        this.value = value;
    }

    public TextComponent() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void bindToQuizTitle() {
        this.value = "{quiz.title}";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
