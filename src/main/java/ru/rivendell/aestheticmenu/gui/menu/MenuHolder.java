package ru.rivendell.aestheticmenu.gui.menu;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.ClickActionConfig;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MenuHolder implements InventoryHolder {

    @Getter private final UUID uniqueId;
    @Getter private final boolean buffer;
    @Getter private HashMap<String, List<ClickActionConfig>> actions;

    private final Inventory link;

    protected MenuHolder(Inventory link, boolean buffer) {
        this.link = link;
        this.buffer = buffer;
        this.uniqueId = UUID.randomUUID();
        actions = new HashMap<>();
    }

    @Override
    public Inventory getInventory() { return this.link; }
}
