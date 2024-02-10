package ru.rivendell.aestheticmenu.commands.impl;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.commands.Command;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;
import ru.rivendell.aestheticmenu.gui.MenuRegistrar;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

import java.util.Objects;

public class ForceOpenCommand extends Command {

    private MenuRegistrar menuRegistrar;

    public ForceOpenCommand(MenuRegistrar menuRegistrar, String permission, MessagesConfig messagesConfig) {
        super(permission, messagesConfig);
        this.menuRegistrar = menuRegistrar;
    }

    @Override
    public boolean onExecute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = Bukkit.getServer().getPlayer(args[0]);
        assert player != null;

        player.openInventory(Objects.requireNonNull(menuRegistrar.getMenuById(args[1])).getInventory());
        return true;
    }
}