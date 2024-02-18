package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class ItemContainerConfig {

    private List<ActionConfig> actions;
    private RequirementsConfig requirement;
    @Getter private String permission;

    public List<ActionConfig> getActions() {
        if(actions == null) return new ArrayList<>();

        return actions;
    }

    public RequirementsConfig getRequirement() {
        if(actions == null) return new RequirementsConfig();

        return requirement;
    }

}
