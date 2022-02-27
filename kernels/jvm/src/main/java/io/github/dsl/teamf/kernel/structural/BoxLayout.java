package io.github.dsl.teamf.kernel.structural;

import io.github.dsl.teamf.kernel.behavioral.UIComponent;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class BoxLayout extends Layout implements Visitable {
    private UIComponent content;
    private String background;

    public BoxLayout(String name) {
        super(name);
    }

    public UIComponent getContent() {
        return content;
    }

    public void setContent(UIComponent content) {
        this.content = content;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
