package io.github.dsl.teamf.antlr;

public class NamedElementNotFoundException extends Exception {
    private static String errorString = "Zone not found :";
    public NamedElementNotFoundException(String zoneNotFound){
        super(errorString + zoneNotFound);
    }
}
