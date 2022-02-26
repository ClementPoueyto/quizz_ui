package io.github.dsl.teamf.kernel.structural;

import io.github.dsl.teamf.kernel.NamedElement;

public class Layout implements NamedElement {
    private String name;

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
    
}
