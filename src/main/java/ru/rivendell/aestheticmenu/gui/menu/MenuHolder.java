package ru.rivendell.aestheticmenu.gui.menu;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public class MenuHolder implements InventoryHolder {

    @Getter private final UUID uniqueId;
    @Getter private final boolean buffer;
    private final Inventory link;

    protected MenuHolder(Inventory link, boolean buffer) {
        this.link = link;
        this.buffer = buffer;
        this.uniqueId = UUID.randomUUID();
    }

    @Override
    public Inventory getInventory() { return this.link; }
}
