package ru.rivendell.aestheticmenu;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rivendell.aestheticmenu.commands.CommandRegistrar;
import ru.rivendell.aestheticmenu.commands.impl.ForceOpenCommand;
import ru.rivendell.aestheticmenu.commands.impl.ReloadCommand;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;
import ru.rivendell.aestheticmenu.config.configurations.gui.GuiConfig;
import ru.rivendell.aestheticmenu.events.HandlersRegistrar;
import ru.rivendell.aestheticmenu.events.impl.InventoryClickHandler;
import ru.rivendell.aestheticmenu.events.impl.InventoryCloseHandler;
import ru.rivendell.aestheticmenu.events.impl.PlayerPickupItemHandler;
import ru.rivendell.aestheticmenu.events.impl.PlayerQuitHandler;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;
import ru.rivendell.aestheticmenu.gui.menu.MenuHolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    @Inject private PlayerInventoriesBuffer playerInventoriesBuffer;
    private MiniMessage mm = MiniMessage.miniMessage();

    public static NamespacedKey COMMANDS_KEY;

    @Override
    public void onEnable() {
        log = getLogger();

        loadDepends();

        COMMANDS_KEY = new NamespacedKey(this, "commands");

        this.injector = Guice.createInjector(new AestheticModule(this));
        this.injector.injectMembers(this);
        log.info("Injector has created successful");

        configRegistrar.loadConfig();
        log.info("Config has been loaded");

        loadMenus();

        commandRegistrar.registerCommand("aestheticmenu-forceopen", new ForceOpenCommand(menuRegistrar, "aestheticmenu.admin.forceopen", configRegistrar.getMessagesConfig(), playerInventoriesBuffer));
        commandRegistrar.registerCommand("aestheticmenu-reload", new ReloadCommand("aestheticmenu.admin.reload", configRegistrar.getMessagesConfig(), this));

        pluginMetrics.setupMetrics();
        registerEvents();
    }

    @Override
    public void onDisable() {
        log.info("Disabling plugin");

        for (Player player : Bukkit.getOnlinePlayers()) {
            if(player.getOpenInventory().getTopInventory() == null) continue;

            if(player.getOpenInventory().getTopInventory().getHolder() instanceof MenuHolder) {
                MenuHolder holder = (MenuHolder) player.getOpenInventory().getTopInventory().getHolder();

                if(holder.isBuffer()) {
                    player.getInventory().setContents(playerInventoriesBuffer.getBuffer().get(player.getUniqueId()));
                    playerInventoriesBuffer.getBuffer().remove(player.getUniqueId());
                }
            }

        }
    }

    public void reload() {

        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            Inventory inventory = onlinePlayer.getOpenInventory().getTopInventory();
            if(inventory != null) {
                if(inventory instanceof MenuHolder) {
                    MenuHolder holder = (MenuHolder) inventory.getHolder();

                    if(holder.isBuffer()) {
                        onlinePlayer.getInventory().setContents(playerInventoriesBuffer.getBuffer().get(onlinePlayer.getUniqueId()));
                        playerInventoriesBuffer.getBuffer().remove(onlinePlayer.getUniqueId());
                    }
                }
            }
        }

        configRegistrar.loadConfig();
        menuRegistrar.clear();

        loadMenus();
    }

    private void loadDepends() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            log.info("PlaceholderAPI hooked successful!");
        }
        else {
            log.severe("PlaceholderAPI not found! Install it to get best experience");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }


    private void loadMenus() {
        File folder = new File(getDataFolder(), "menus/");
        if (folder.isFile()) return;
        ;

        File[] files = folder.listFiles();

        for (File file : files) {
            menuRegistrar.registerMenu(configLoader.load("menus/" + file.getName().replace(".json", ""), GuiConfig.class));
            log.info("Loading menu " + file.getName());
        }
    }

    private void registerEvents() {
        handlersRegistrar.registerEvent(new InventoryClickHandler());
        handlersRegistrar.registerEvent(new InventoryCloseHandler(playerInventoriesBuffer));
        handlersRegistrar.registerEvent(new PlayerQuitHandler(playerInventoriesBuffer));
        handlersRegistrar.registerEvent(new PlayerPickupItemHandler());
    }

}
