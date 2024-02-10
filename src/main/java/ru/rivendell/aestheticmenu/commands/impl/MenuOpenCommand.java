package ru.rivendell.aestheticmenu.commands.impl;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.commands.Command;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;
import ru.rivendell.aestheticmenu.gui.menu.MenuInventory;

public class MenuOpenCommand extends Command {

    private MenuInventory menu;

    public MenuOpenCommand(MenuInventory menu, String permission, MessagesConfig messagesConfig) {
        super(permission, messagesConfig);
        this.menu = menu;
    }

    @Override
    public boolean onExecute(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        player.openInventory(menu.getInventory());
        return true;
    }
}