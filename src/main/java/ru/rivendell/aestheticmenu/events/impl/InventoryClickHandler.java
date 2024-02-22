package ru.rivendell.aestheticmenu.events.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.ClickActionConfig;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;

import java.lang.reflect.Modifier;
import java.util.List;

public class InventoryClickHandler implements Listener {

    private Gson deserializer;

    public InventoryClickHandler() {
        deserializer = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getInventory().getHolder() instanceof MenuHolder)) return;
        if(event.getCurrentItem() == null) return;

        Player player = (Player) event.getWhoClicked();
        PersistentDataContainer container = event.getCurrentItem().getItemMeta().getPersistentDataContainer();

        if(container.has(AestheticMenu.COMMANDS_KEY, PersistentDataType.STRING)) {
            String id = container.get(AestheticMenu.COMMANDS_KEY, PersistentDataType.STRING);
            MenuHolder holder = (MenuHolder) event.getInventory().getHolder();

            for (ClickActionConfig clickActionConfig : holder.getActions().get(id)) {
                clickActionConfig.execute(player);
            }

        }

        event.setCancelled(true);
    }
}
