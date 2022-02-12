package io.github.dsl.teamf.kernel.structural;

import io.github.dsl.teamf.kernel.NamedElement;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;


public class Zone implements NamedElement, Visitable {

    private String name;
    private Color color;
    private int[] start;
    private int[] end;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int[] getStart() {
        return start;
    }

    public void setStart(int[] start) {
        this.start = start;
    }

    public int[] getEnd() {
        return end;
    }

    public void setEnd(int[] end) {
        this.end = end;
    }
}
