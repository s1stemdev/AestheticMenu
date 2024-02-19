package ru.rivendell.aestheticmenu.commands.admin;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;
import revxrsal.commands.command.CommandActor;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.configurations.ConfigRegistrar;
import ru.rivendell.aestheticmenu.utils.TextSerializer;

@Singleton
public class ReloadCommand {

    @Inject private AestheticMenu plugin;
    @Inject private ConfigRegistrar configRegistrar;


    @Command({"aestheticmenu reload", "am reload", "amenu reload"})
    @CommandPermission("aestheticmenu.admin.reload")
    public void reload(CommandActor actor) {
        plugin.reload();
        actor.reply(TextSerializer.serializeSection(configRegistrar.getMessagesConfig().getSystem().getReloadMessage()));
    }

}
