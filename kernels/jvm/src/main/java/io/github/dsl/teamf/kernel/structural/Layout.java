package io.github.dsl.teamf.kernel.structural;

import io.github.dsl.teamf.kernel.NamedElement;
import io.github.dsl.teamf.kernel.generator.Visitable;
import io.github.dsl.teamf.kernel.generator.Visitor;

public class Layout implements NamedElement, Visitable {
    private String name;
    private String gridArea;

    public Layout(String name) {
        this.name = name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getGridArea() {
        return gridArea;
    }

    public void setGridArea(String gridArea) {
        this.gridArea = gridArea;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
