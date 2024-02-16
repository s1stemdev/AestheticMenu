package ru.rivendell.aestheticmenu.events.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.InventoryHolder;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;

public class PlayerPickupItemHandler implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        InventoryHolder holder = player.getOpenInventory().getTopInventory().getHolder();

        if(holder instanceof MenuHolder) return;
        if(!((MenuHolder) holder).isBuffer()) return;

        event.setCancelled(true);
    }

}
