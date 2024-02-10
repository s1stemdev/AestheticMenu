package ru.rivendell.aestheticmenu.commands.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

public class MenuOpenCommand implements CommandExecutor {

    private MenuInventory menu;
    private MessagesConfig messagesConfig;

    public MenuOpenCommand(MenuInventory menu, MessagesConfig messagesConfig) {
        this.menu = menu;
        this.messagesConfig = messagesConfig;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(messagesConfig.getErrors().getOnlyPlayers());
            return true;
        }

        Player player = (Player) commandSender;
        player.openInventory(menu.getInventory());
        return true;
    }

}
