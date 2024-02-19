package ru.rivendell.aestheticmenu.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;
import ru.rivendell.aestheticmenu.utils.TextSerializer;

import java.util.Arrays;
import java.util.List;

public class MenuCommand extends Command{

    private MenuInventory menu;

    public MenuCommand(MenuInventory menu) {
        super(menu.getGui().getCommands()[0], "Menu open command", "/" + menu.getGui().getCommands()[0], Arrays.asList(menu.getGui().getCommands()));
        this.menu = menu;
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
        if(menu == null) return true;

        Player player = (Player) commandSender;
        menu.openCustomInventory(menu.build(player), player);

        return true;
    }
}
