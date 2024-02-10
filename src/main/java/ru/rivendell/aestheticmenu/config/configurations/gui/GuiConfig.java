package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.config.Configurable;

import java.util.HashMap;

@Getter @Setter
public class GuiConfig extends Configurable {

    private String menuId;
    private String title;
    private byte size;
    private boolean usePlayerInventory;
    private HashMap<Integer, ItemConfig> items;

    @Override
    protected String getName() {
        return null;
    }
}
