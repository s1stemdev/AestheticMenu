package ru.rivendell.aestheticmenu.commands.impl;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.commands.Command;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

public class ReloadCommand extends Command {

    private AestheticMenu plugin;

    public ReloadCommand(String permission, MessagesConfig messagesConfig, AestheticMenu plugin) {
        super(permission, messagesConfig);
        this.plugin = plugin;
    }
    @Override
    public boolean onExecute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        plugin.reload();
        return true;
    }
}
