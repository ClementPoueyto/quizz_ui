package io.github.dsl.teamf.kernel.structural;

public enum Size {
    XXSMALL("xxsmall"),
    XSMALL("xsmall"),
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large"),
    XLARGE("xlarge"),
    AUTO("auto");

    private String value;

    Size(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
