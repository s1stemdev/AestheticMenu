package ru.rivendell.aestheticmenu.commands.admin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Usage;
import revxrsal.commands.bukkit.annotation.CommandPermission;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

@Singleton
public class ForceOpenCommand {

    @Inject private MenuRegistrar menuRegistrar;

    @Command({"aestheticmenu forceopen", "am forceopen", "amenu forceopen"})
    @CommandPermission("aestheticmenu.admin.forceopen")
    @Usage("aestheticmenu forceopen <player_name> <menu_id>")
    public void forceOpen(String playerName, String menuId) {
        Player player = Bukkit.getPlayer(playerName);

        MenuInventory menu = menuRegistrar.getMenuById(menuId);
        if(menu == null) return;

        menu.openCustomInventory(menu.build(player), player);
    }

}
