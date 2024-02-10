package ru.rivendell.aestheticmenu.config.configurations;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.Getter;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.ConfigLoader;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;

public class ConfigRegistrar {

    @Inject private AestheticMenu plugin;
    @Inject private ConfigLoader loader;

    @Getter private MessagesConfig messagesConfig;

    public ConfigRegistrar() { }

    public void loadConfig() {
        plugin.saveResource("messages.json", false);
        messagesConfig = loader.load("messages", MessagesConfig.class);
    }

}
