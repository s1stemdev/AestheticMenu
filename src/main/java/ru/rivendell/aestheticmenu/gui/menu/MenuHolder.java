package ru.rivendell.aestheticmenu.gui.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public class MenuHolder implements InventoryHolder {

    private final UUID uniqueId;
    private final Inventory link;

    protected MenuHolder(Inventory link) {
        this.link = link;
        this.uniqueId = UUID.randomUUID();
    }

    public UUID uniqueId() { return this.uniqueId; }
    @Override
    public Inventory getInventory() { return this.link; }
}
