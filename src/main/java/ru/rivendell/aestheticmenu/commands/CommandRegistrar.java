package ru.rivendell.aestheticmenu.commands;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.rivendell.aestheticmenu.AestheticMenu;

@Singleton
public class CommandRegistrar {

    @Inject private AestheticMenu plugin;

    public CommandRegistrar() { }

    public void registerCommand(String name, Command command) {
        plugin.getCommand(name).setExecutor(command);
    }

    public void registerDynamicCommand(String name, Command command) {
        
    }

}
