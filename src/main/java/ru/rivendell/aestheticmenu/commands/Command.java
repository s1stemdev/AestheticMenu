package ru.rivendell.aestheticmenu.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.config.configurations.messages.MessagesConfig;

public abstract class Command implements CommandExecutor {
    private String permission;
    protected MiniMessage mm = MiniMessage.miniMessage();
    protected PlainTextComponentSerializer plain = PlainTextComponentSerializer.plainText();
    protected MessagesConfig messagesConfig;

    public Command(String permission, MessagesConfig messagesConfig) {
        this.permission = permission;
        this.messagesConfig = messagesConfig;
    }

    public abstract boolean onExecute(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if(permission != null) {
            if(!commandSender.hasPermission(permission)) return true;
        }

        return onExecute(commandSender, command, s, strings);
    }

    protected String serializeMessage(String message) {
        return plain.serialize(mm.deserialize(message));
    }
}
