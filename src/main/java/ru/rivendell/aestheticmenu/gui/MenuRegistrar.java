package ru.rivendell.aestheticmenu.gui;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.gui.GuiConfig;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

import javax.annotation.Nullable;
import java.util.List;

@Singleton
public class MenuRegistrar {

    @Inject private ConfigLoader configLoader;
    @Inject private MiniMessage mm;
    @Inject private PlainTextComponentSerializer plain;

    @Getter private List<MenuInventory> menus;

    public MenuRegistrar() { }

    public void registerMenu(GuiConfig config) {
        MenuInventory menu = new MenuInventory(config, mm, plain);
        menus.add(menu);
    }

    @Nullable public MenuInventory getMenuById(String name) {
        for (MenuInventory menu : menus) {
            if(menu.getGui().getMenuId().equals(name)) {
                return menu;
            }
        }
        return null;
    }

}
