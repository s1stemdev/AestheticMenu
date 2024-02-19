package ru.rivendell.aestheticmenu.gui;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.commands.player.MenuCommand;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.gui.GuiConfig;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MenuRegistrar {

    @Inject private ConfigLoader configLoader;
    @Inject private PlayerInventoriesBuffer playerInventoriesBuffer;
    @Inject private AestheticMenu plugin;


    @Getter private List<MenuInventory> menus;

    public MenuRegistrar() { menus = new ArrayList<>(); }

    public void clear() {
        menus.clear();
    }

    public void registerMenu(GuiConfig config) throws NoSuchFieldException, IllegalAccessException {
        MenuInventory menu = new MenuInventory(config, playerInventoriesBuffer);

        final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);

        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

        commandMap.register(menu.getGui().getCommands()[0], new MenuCommand(menu));

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
