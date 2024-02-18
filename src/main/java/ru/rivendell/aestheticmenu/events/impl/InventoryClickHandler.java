package ru.rivendell.aestheticmenu.events.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.source.tree.BreakTree;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.configurations.gui.ActionConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.ItemContainerConfig;
import ru.rivendell.aestheticmenu.enums.ActionType;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

import java.lang.reflect.Modifier;
import java.util.List;

public class InventoryClickHandler implements Listener {

    private Gson deserializer;
    private LegacyComponentSerializer legacy;
    private MiniMessage miniMessage;

    public InventoryClickHandler() {
        deserializer = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                //.registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
        legacy = LegacyComponentSerializer.legacySection();
        miniMessage = MiniMessage.miniMessage();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getInventory().getHolder() instanceof MenuHolder)) return;
        if(event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();

        ItemContainerConfig config = deserializer.fromJson(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(AestheticMenu.COMMANDS_KEY, PersistentDataType.STRING), ItemContainerConfig.class);
        if(config.getPermission() == null || player.hasPermission(config.getPermission())) {

            if(config.getRequirement() == null || config.getRequirement().result(player)) execute(player, config);

        }

        event.setCancelled(true);
    }

    private void execute(Player player, ItemContainerConfig config) {

        for (ActionConfig action : config.getActions()) {

            switch (action.getType()) {
                case MESSAGE: {
                    player.sendMessage(legacy.serialize(miniMessage.deserialize(PlaceholderAPI.setPlaceholders(player, action.getData()))));
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
