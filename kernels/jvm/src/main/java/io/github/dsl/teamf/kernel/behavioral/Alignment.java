package io.github.dsl.teamf.kernel.behavioral;

public enum Alignment {
    CENTER("center"), START("start"), END("end");

    private String value;

    Alignment(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
