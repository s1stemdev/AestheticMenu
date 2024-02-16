package ru.rivendell.aestheticmenu.events.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.configurations.gui.ItemContainerConfig;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

import java.lang.reflect.Modifier;
import java.util.List;

public class InventoryClickHandler implements Listener {

    private Gson deserializer;

    public InventoryClickHandler() {
        deserializer = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                //.registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getInventory().getHolder() instanceof MenuHolder)) return;

        ItemContainerConfig config = deserializer.fromJson(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(AestheticMenu.COMMANDS_KEY, PersistentDataType.STRING), ItemContainerConfig.class);

        executeCommands(Bukkit.getConsoleSender(), config.getCommands());
        event.setCancelled(true);
    }

    private void executeCommands(CommandSender sender, List<String> commands) {
        for (String command : commands) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }

}
