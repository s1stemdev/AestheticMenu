package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ItemContainerConfig {

    private List<ActionConfig> actions;
    private RequirementsConfig requirement;
    private String permission;

}
