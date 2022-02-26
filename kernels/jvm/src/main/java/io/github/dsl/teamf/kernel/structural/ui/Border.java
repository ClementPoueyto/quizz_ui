package io.github.dsl.teamf.kernel.structural.ui;

import io.github.dsl.teamf.kernel.NamedElement;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Border implements Visitable {
    String color;
    Size size;
    String style;
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

}