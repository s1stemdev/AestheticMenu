package ru.rivendell.aestheticmenu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class AestheticMenu extends JavaPlugin {

    @Getter private Logger log;

    @Override
    public void onEnable() {
        log = Bukkit.getLogger();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
