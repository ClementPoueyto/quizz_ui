package io.github.dsl.teamf.kernel.behavioral;

import io.github.dsl.teamf.kernel.structural.ui.Size;

public enum ScreenSize {
    PHONE(Size.small),
    TABLET(Size.medium),
    COMPUTER(Size.large);

    private final Size grommetName;

    public String getGrommetName(){
        return this.grommetName.toString();
    }
    private ScreenSize(Size grommetName) {
        this.grommetName = grommetName;
    }
}
