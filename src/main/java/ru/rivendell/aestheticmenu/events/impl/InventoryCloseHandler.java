package ru.rivendell.aestheticmenu.events.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;

public class InventoryCloseHandler implements Listener {

    private PlayerInventoriesBuffer playerInventoriesBuffer;

    public InventoryCloseHandler(PlayerInventoriesBuffer playerInventoriesBuffer) {
        this.playerInventoriesBuffer = playerInventoriesBuffer;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {

        if(event.getInventory().getHolder() instanceof MenuHolder) {
            Player player = (Player) event.getPlayer();

            MenuHolder holder = (MenuHolder) event.getInventory().getHolder();

            if(holder.isBuffer()) {
                player.getInventory().setContents(playerInventoriesBuffer.getBuffer().get(player.getUniqueId()));
                playerInventoriesBuffer.getBuffer().remove(player.getUniqueId());
            }
        }
    }
}
