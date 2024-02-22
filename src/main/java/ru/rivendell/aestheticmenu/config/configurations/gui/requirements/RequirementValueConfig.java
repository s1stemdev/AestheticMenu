package ru.rivendell.aestheticmenu.config.configurations.gui.requirements;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.enums.RequirementTypes;

@Setter
public class RequirementValueConfig {

    private RequirementTypes type;
    @Getter private String first;
    @Getter private String value;
    private String symbol;
    @Getter private String second;

    public RequirementValueConfig() { }

    public RequirementValueConfig(RequirementTypes type, String first, String symbol, String second) {
        this.type = type;
        this.first = first;
        this.symbol = symbol;
        this.second = second;
    }

    public RequirementTypes getType() {
        if(type == null) return RequirementTypes.NUM;
        return type;
    }

    public String getSymbol() {
        if(symbol == null) return "==";
        return symbol;
    }
}
