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
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;

import java.util.logging.Logger;

@Singleton
public final class AestheticMenu extends JavaPlugin {

    @Getter private Logger log;
    @Getter private Injector injector;


    @Inject private ConfigLoader configLoader;
    @Inject private ConfigRegistrar configRegistrar;
    @Inject private CommandRegistrar commandRegistrar;
    @Inject private MenuRegistrar menuRegistrar;
    @Inject private MiniMessage mm;

    @Override
    public void onEnable() {
        log = getLogger();
        this.injector = Guice.createInjector(new AestheticModule(this));
        this.injector.injectMembers(this);
        log.info("Injector has created successful");

        configRegistrar.loadConfig();
        log.info("Config has been loaded");

        commandRegistrar.registerCommand("aestheticmenu-forceopen", new ForceOpenCommand(menuRegistrar, "aestheticmenu.admin", configRegistrar.getMessagesConfig()));
        commandRegistrar.registerCommand("aestheticmenu-reload", new ForceOpenCommand(menuRegistrar, "aestheticmenu.admin", configRegistrar.getMessagesConfig()));
    }

    @Override
    public void onDisable() {
        log.info("Disabling plugin");
    }

}
