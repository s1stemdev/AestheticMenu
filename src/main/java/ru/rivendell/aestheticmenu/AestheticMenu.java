package ru.rivendell.aestheticmenu;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rivendell.aestheticmenu.commands.CommandRegistrar;
import ru.rivendell.aestheticmenu.commands.impl.ForceOpenCommand;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;
import ru.rivendell.aestheticmenu.config.configurations.gui.GuiConfig;
import ru.rivendell.aestheticmenu.events.HandlersRegistrar;
import ru.rivendell.aestheticmenu.events.impl.InventoryClickHandler;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;

import java.io.File;
import java.util.logging.Logger;

@Singleton
public final class AestheticMenu extends JavaPlugin {

    @Getter private Logger log;
    @Getter private Injector injector;


    @Inject private ConfigLoader configLoader;
    @Inject private ConfigRegistrar configRegistrar;
    @Inject private CommandRegistrar commandRegistrar;
    @Inject private HandlersRegistrar handlersRegistrar;
    @Inject private MenuRegistrar menuRegistrar;
    @Inject private PluginMetrics pluginMetrics;

    private MiniMessage mm = MiniMessage.miniMessage();

    @Override
    public void onEnable() {
        log = getLogger();
        this.injector = Guice.createInjector(new AestheticModule(this));
        this.injector.injectMembers(this);
        log.info("Injector has created successful");

        configRegistrar.loadConfig();
        log.info("Config has been loaded");

        loadMenus();

        commandRegistrar.registerCommand("aestheticmenu-forceopen", new ForceOpenCommand(menuRegistrar, "aestheticmenu.admin", configRegistrar.getMessagesConfig()));

        pluginMetrics.setupMetrics();
        registerEvents();
    }

    @Override
    public void onDisable() {
        log.info("Disabling plugin");
    }


    private void loadMenus() {
        File folder = new File(getDataFolder(), "menus/");
        if(folder.isFile()) return;;

        try {

            File[] files = folder.listFiles();

            for (File file : files) {
                menuRegistrar.registerMenu(configLoader.load("menus/" + file.getName().replace(".json", ""), GuiConfig.class));
                log.info("Loading menu " + file.getName());
            }

        } catch (Exception e) {
            log.severe(e.getMessage());
        }
    }

    private void registerEvents() {
        handlersRegistrar.registerEvent(new InventoryClickHandler());
    }

}
