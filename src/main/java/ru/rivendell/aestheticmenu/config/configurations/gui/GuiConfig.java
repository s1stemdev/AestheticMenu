package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import ru.rivendell.aestheticmenu.config.Configurable;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.ItemConfig;

import java.util.HashMap;

@Setter
public class GuiConfig extends Configurable {

    @Getter private String menuId;
    private String[] commands;
    private String title;
    @Getter private byte size;
    @Getter private boolean usePlayerInventory;
    @Getter private GuiOpenRequirementsConfig openRequirements;
    private HashMap<String, ItemConfig> items;
    private HashMap<String, ItemConfig> playerInvItems;

    @Override
    protected String getName() {
        return null;
    }

    public String[] getCommands() {
        if(commands == null) return new String[]{ menuId + "_default" };
        return commands;
    }

    public String getTitle() {
        if(title == null) return menuId + " has no title";
        return title;
    }

    public HashMap<String, ItemConfig> getItems() {
        if(items == null) return new HashMap<>();

        return items;
    }

    public HashMap<String, ItemConfig> getPlayerInvItems() {
        if(playerInvItems == null) return new HashMap<>();

        return playerInvItems;
    }
}
