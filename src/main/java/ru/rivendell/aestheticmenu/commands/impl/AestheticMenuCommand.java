package ru.rivendell.aestheticmenu.commands.impl;

import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;

public class AestheticMenuCommand implements CommandExecutor {

    @Inject private MessagesConfig messagesConfig;
    @Inject private ConfigRegistrar configRegistrar;
    @Inject private MenuRegistrar menuRegistrar;


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!commandSender.hasPermission("aestheticmenu.admin")) {
            commandSender.sendMessage(messagesConfig.getErrors().getNoPermission());
            return true;
        }

        if(strings.length < 1) {
            commandSender.sendMessage(messagesConfig.getErrors().getNotEnoughArgs());
            return true;
        }

        switch (strings[0]) {
            case "reload": {
                configRegistrar.loadConfig();
                commandSender.sendMessage(messagesConfig.getSystem().getReloadMessage());
            }
            case "open": {
                if(strings.length == 2) {

                    if(!(commandSender instanceof Player)) {
                        commandSender.sendMessage(messagesConfig.getErrors().getOnlyPlayers());
                        return true;
                    }
                    ((Player) commandSender).openInventory(menuRegistrar.getMenuById(strings[1]).getInventory());
                }
                if(strings.length == 3) {
                    Player target = Bukkit.getServer().getPlayer(strings[2]);
                    target.openInventory(menuRegistrar.getMenuById(strings[1]).getInventory());
                }

            }
        }
        return true;
    }
}
