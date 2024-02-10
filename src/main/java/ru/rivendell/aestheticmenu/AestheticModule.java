package ru.rivendell.aestheticmenu;

import com.google.inject.AbstractModule;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;

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
    }
}
