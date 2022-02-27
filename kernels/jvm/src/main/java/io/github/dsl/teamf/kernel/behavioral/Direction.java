package io.github.dsl.teamf.kernel.behavioral;

public enum Direction {
    ROW("row"),
    COLUMN( "column"),
    ROW_REVERSE("row-reverse"),
    COLUMN_REVERSE("column-reverse"),
    ROW_RESPONSIVE("row-responsive");




    private String value;

    Direction(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
