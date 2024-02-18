package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequirementValueConfig {

    private String first;
    private String symbol;
    private String second;

    public RequirementValueConfig() { }

    public RequirementValueConfig(String first, String symbol, String second) {
        this.first = first;
        this.symbol = symbol;
        this.second = second;
    }

}
