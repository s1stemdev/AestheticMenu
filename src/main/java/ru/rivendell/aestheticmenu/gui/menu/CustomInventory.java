package ru.rivendell.aestheticmenu.gui.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public class CustomInventory {

    private Inventory chestInventory;
    private ItemStack[] playerContents;

    public CustomInventory(Inventory chestInventory, ItemStack[] playerContents) {
        this.chestInventory = chestInventory;
        this.playerContents = playerContents;
    }

    public CustomInventory(Inventory chestInventory) {
        this.chestInventory = chestInventory;
        playerContents = null;
    }
}
