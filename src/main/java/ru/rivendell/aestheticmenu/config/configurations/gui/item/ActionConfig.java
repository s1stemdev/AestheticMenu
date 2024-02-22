package ru.rivendell.aestheticmenu.config.configurations.gui.item;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.enums.ActionType;

@Getter @Setter
public class ActionConfig {

    private ActionType type;
    private String data;



}
