package ru.rivendell.aestheticmenu.events;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import ru.rivendell.aestheticmenu.AestheticMenu;

@Singleton
public class HandlersRegistrar {

    @Inject private AestheticMenu plugin;

    public HandlersRegistrar() { }

    public void registerEvent(Listener event) {
        Bukkit.getPluginManager().registerEvents(event, plugin);
    }

}
