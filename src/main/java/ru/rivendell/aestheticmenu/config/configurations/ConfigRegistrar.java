package ru.rivendell.aestheticmenu.config.configurations;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.config.MainConfig;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;

public class ConfigRegistrar {

    @Inject private AestheticMenu plugin;
    @Inject private ConfigLoader loader;

    @Getter private MessagesConfig messagesConfig;
    @Getter private MainConfig mainConfig;

    public ConfigRegistrar() { }

    public void loadConfig() {
        plugin.saveResource("messages.json", false);
        plugin.saveResource("config.json", false);

        messagesConfig = loader.load("messages", MessagesConfig.class);
        mainConfig = loader.load("config", MainConfig.class);

        if(mainConfig.isCopyExampleMenu()) plugin.saveResource("menus/MENU_EXAMPLE_DISABLE_COPY_IN_CONFIG.json", false);
    }

}
