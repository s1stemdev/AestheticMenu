package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.config.Configurable;

import java.util.HashMap;

@Getter @Setter
public class GuiConfig extends Configurable {

    private String menuId;
    private String command;
    private String title;
    private byte size;
    private boolean usePlayerInventory;
    private HashMap<String, ItemConfig> items;
    private HashMap<String, ItemConfig> playerInvItems;

    @Override
    protected String getName() {
        return null;
    }
}
