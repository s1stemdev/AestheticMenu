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
import revxrsal.commands.bukkit.BukkitCommandHandler;
import ru.rivendell.aestheticmenu.commands.admin.ForceOpenCommand;
import ru.rivendell.aestheticmenu.commands.admin.ReloadCommand;
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
import ru.rivendell.aestheticmenu.utils.TextSerializer;

import java.io.File;
import java.util.logging.Logger;

@Singleton
public final class AestheticMenu extends JavaPlugin {

    @Getter private Logger log;
    @Getter private Injector injector;


    @Inject private ConfigLoader configLoader;
    @Inject private ConfigRegistrar configRegistrar;
    @Inject private HandlersRegistrar handlersRegistrar;
    @Inject private MenuRegistrar menuRegistrar;
    @Inject private PluginMetrics pluginMetrics;
    @Inject private PlayerInventoriesBuffer playerInventoriesBuffer;


    @Inject private ReloadCommand reloadCommand;
    @Inject private ForceOpenCommand forceOpenCommand;


    public static NamespacedKey COMMANDS_KEY;

    @Override
    public void onEnable() {
        log = getLogger();

        log.info(TextSerializer.serializeAnsi("<#FBA7E9>\n" +
                "<#FBA7E9>▄▀█ █▀▀ █▀ ▀█▀ █░█ █▀▀ ▀█▀ █ █▀▀\n" +
                "<#FBA7E9>█▀█ ██▄ ▄█ ░█░ █▀█ ██▄ ░█░ █ █▄▄\n" +
                "<#FBA7E9>\n" +
                "<#FBA7E9>░ ░ ░ █▀▄▀█ █▀▀ █▄░█ █░█ ░ ░ ░\n" +
                "<#FBA7E9>▄ ▄ ▄ █░▀░█ ██▄ █░▀█ █▄█ ▄ ▄ ▄"));

        loadDepends();

        COMMANDS_KEY = new NamespacedKey(this, "commands");

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.injector = Guice.createInjector(new AestheticModule(this));
        this.injector.injectMembers(this);
        log.info(TextSerializer.serializeAnsi("<#FBA7E9><bold>Injector has created successful"));

        configRegistrar.loadConfig();
        log.info(TextSerializer.serializeAnsi("<#FBA7E9><bold>Config has been loaded"));

        loadMenus();

        pluginMetrics.setupMetrics();
        registerEvents();

        BukkitCommandHandler handler = BukkitCommandHandler.create(this);
        handler.register(reloadCommand);
        handler.register(forceOpenCommand);

    }

    @Override
    public void onDisable() {
        log.info(TextSerializer.serializeAnsi("<#FBA7E9><bold>Disabling plugin"));

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

        configRegistrar.loadConfig();
        menuRegistrar.clear();

        loadMenus();
    }

    private void loadDepends() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            log.info(TextSerializer.serializeAnsi("<#FBA7E9><bold>PlaceholderAPI hooked successful!"));
        }
        else {
            log.severe(TextSerializer.serializeAnsi("<#FBA7E9><bold>PlaceholderAPI not found! Install it to get best experience"));
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }


    private void loadMenus() {
        File folder = new File(getDataFolder(), "menus/");
        if (folder.isFile()) return;
        ;

        File[] files = folder.listFiles();

        for (File file : files) {
            try {
                menuRegistrar.registerMenu(configLoader.load("menus/" + file.getName().replace(".json", ""), GuiConfig.class));
                log.info(TextSerializer.serializeAnsi("<#FBA7E9><bold>Loading menu " + file.getName()));
            } catch (Exception e) {
                log.severe(e.getMessage());
            }

        }
    }

    private void registerEvents() {
        handlersRegistrar.registerEvent(new InventoryClickHandler());
        handlersRegistrar.registerEvent(new InventoryCloseHandler(playerInventoriesBuffer));
        handlersRegistrar.registerEvent(new PlayerQuitHandler(playerInventoriesBuffer));
        handlersRegistrar.registerEvent(new PlayerPickupItemHandler());
    }

}
