package ru.rivendell.aestheticmenu.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.ActionConfig;

import java.util.List;

public class ActionExecutor {

    public static void executeActions(Player player, List<ActionConfig> actions) {

        for (ActionConfig action : actions) {

            switch (action.getType()) {
                case MESSAGE: {
                    player.sendMessage(TextSerializer.serializeSection(PlaceholderAPI.setPlaceholders(player, action.getData())));
                    break;
                }
                case PLAYER: {
                    Bukkit.getServer().dispatchCommand(player, PlaceholderAPI.setPlaceholders(player, action.getData()));
                    break;
                }
                case CONSOLE: {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), PlaceholderAPI.setPlaceholders(player, action.getData()));
                    break;
                }
                case SOUND: {
                    player.playSound(player.getLocation(), Sound.valueOf(action.getData()), 1f, 1f);
                }
                case CLOSE: {
                    player.closeInventory();
                    break;
                }
            }
        }
    }

}
