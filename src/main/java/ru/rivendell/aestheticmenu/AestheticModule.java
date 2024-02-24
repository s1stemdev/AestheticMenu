package ru.rivendell.aestheticmenu;

import com.google.inject.AbstractModule;
import ru.rivendell.aestheticmenu.commands.admin.ForceOpenCommand;
import ru.rivendell.aestheticmenu.commands.admin.ReloadCommand;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;
import ru.rivendell.aestheticmenu.events.HandlersRegistrar;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;
import ru.rivendell.aestheticmenu.utils.ProxySender;

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
        bind(HandlersRegistrar.class).toInstance(new HandlersRegistrar());
        bind(MenuRegistrar.class).toInstance(new MenuRegistrar());
        bind(PluginMetrics.class).toInstance(new PluginMetrics());
        bind(PlayerInventoriesBuffer.class).toInstance(new PlayerInventoriesBuffer());

        bind(ReloadCommand.class).toInstance(new ReloadCommand());
        bind(ForceOpenCommand.class).toInstance(new ForceOpenCommand());
    }
}
