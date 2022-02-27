package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.NamedElement;

public class Theme implements NamedElement {
    private String name;
    private String primaryColor;
    private String secondaryColor;
    private String font;

    public Theme(String name) {
        this.name = name;
    }

    public Theme(String name, String primaryColor, String secondaryColor, String font) {
        this.name = name;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.font = font;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    @Override
    public String toString() {
        return "Theme [font=" + font + ", name=" + name + ", primaryColor=" + primaryColor + ", secondaryColor="
                + secondaryColor + "]";
    }
}
