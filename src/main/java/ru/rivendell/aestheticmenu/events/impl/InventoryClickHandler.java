package ru.rivendell.aestheticmenu.events.impl;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;

public class InventoryClickHandler implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getInventory().getHolder() instanceof MenuHolder) event.setCancelled(true);
    }

}
