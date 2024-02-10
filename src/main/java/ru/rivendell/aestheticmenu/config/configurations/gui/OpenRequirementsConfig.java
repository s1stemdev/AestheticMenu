package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.enums.Requirement;

@Getter @Setter
public class OpenRequirementsConfig {

    private Requirement requirement;
    private boolean invert;

}
