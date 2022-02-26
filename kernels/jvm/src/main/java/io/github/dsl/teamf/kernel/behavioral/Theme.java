package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.NamedElement;

public class Theme implements NamedElement {
    private String name;

    public Theme(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
