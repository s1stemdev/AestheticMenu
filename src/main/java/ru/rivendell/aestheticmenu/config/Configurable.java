package ru.rivendell.aestheticmenu.config;

import org.bukkit.plugin.java.JavaPlugin;
import ru.rivendell.aestheticmenu.AestheticMenu;

public abstract class Configurable {

    public AestheticMenu getPlugin() {
        return JavaPlugin.getPlugin(AestheticMenu.class);
    }

    protected abstract String getName();

}
