package ru.rivendell.aestheticmenu;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;

import java.util.logging.Logger;

public final class AestheticMenu extends JavaPlugin {

    @Getter private Logger log;
    @Getter private Injector injector;

    @Inject private ConfigLoader configLoader;
    @Inject private ConfigRegistrar configRegistrar;

    @Override
    public void onEnable() {
        log = getLogger();
        this.injector = Guice.createInjector(new AestheticModule(this));
        this.injector.injectMembers(this);

        configRegistrar.loadConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
