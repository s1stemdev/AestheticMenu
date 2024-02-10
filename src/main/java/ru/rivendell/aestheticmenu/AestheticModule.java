package ru.rivendell.aestheticmenu;

import com.google.inject.AbstractModule;
import ru.rivendell.aestheticmenu.commands.CommandRegistrar;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;
import ru.rivendell.aestheticmenu.events.HandlersRegistrar;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;

public class AestheticModule extends AbstractModule {

    private AestheticMenu plugin;

    public AestheticModule(AestheticMenu plugin) {
        this.plugin = plugin;
    }
    @Override
    protected void configure() {
        bind(AestheticMenu.class).toInstance(plugin);
        bind(ConfigLoader.class).toInstance(new ConfigLoader());
        bind(ConfigRegistrar.class).toInstance(new ConfigRegistrar());
        bind(CommandRegistrar.class).toInstance(new CommandRegistrar());
        bind(HandlersRegistrar.class).toInstance(new HandlersRegistrar());
        bind(MenuRegistrar.class).toInstance(new MenuRegistrar());
        bind(PluginMetrics.class).toInstance(new PluginMetrics());
        bind(PlayerInventoriesBuffer.class).toInstance(new PlayerInventoriesBuffer());
    }
}
