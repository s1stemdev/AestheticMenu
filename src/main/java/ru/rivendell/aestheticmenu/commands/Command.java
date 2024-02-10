package ru.rivendell.aestheticmenu.commands;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.rivendell.aestheticmenu.enums.Executor;

public abstract class Command implements CommandExecutor {

    private Executor executor;
    private String permission;
    protected MiniMessage mm;
    protected PlainTextComponentSerializer plain;

    public Command(Executor executor, String permission, MiniMessage mm, PlainTextComponentSerializer plain) {
        this.executor = executor;
        this.permission = permission;
        this.mm = mm;
        this.plain = plain;
    }

    public abstract boolean onExecute(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] args);

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] strings) {
        if(permission != null) {
            if(!commandSender.hasPermission(permission)) return true;
        }

        if(executor == Executor.PLAYER && !(commandSender instanceof Player)) return true;
        if(executor == Executor.CONSOLE && !(commandSender instanceof ConsoleCommandSender)) return true;

        return onExecute(commandSender, command, s, strings);
    }

    protected String serializeMessage(String message) {
        return plain.serialize(mm.deserialize(message));
    }
}
