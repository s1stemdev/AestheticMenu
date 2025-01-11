package ru.rivendell.aestheticmenu.events.impl;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;

public class PlayerQuitHandler implements Listener {

    private PlayerInventoriesBuffer playerInventoriesBuffer;

    public PlayerQuitHandler(PlayerInventoriesBuffer playerInventoriesBuffer) {
        this.playerInventoriesBuffer = playerInventoriesBuffer;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(player.getOpenInventory().getTopInventory() == null) return;

        if(player.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) {
            MenuHolder holder = (MenuHolder) player.getOpenInventory().getTopInventory().getHolder();

            if(holder.isBuffer()) {
                player.getInventory().setContents(playerInventoriesBuffer.getBuffer().get(player.getUniqueId()));
                playerInventoriesBuffer.getBuffer().remove(player.getUniqueId());
            }
        }
    }

}
