package ru.rivendell.aestheticmenu.config.configurations.config;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.config.Configurable;

@Getter @Setter
public class MainConfig extends Configurable {

    private boolean copyExampleMenu;

    @Override
    protected String getName() {
        return "config";
    }
}
