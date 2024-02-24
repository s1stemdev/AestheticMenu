package ru.rivendell.aestheticmenu.utils;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.rivendell.aestheticmenu.AestheticMenu;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ProxySender {

    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            out.writeUTF("Connect");
            out.writeUTF(server);

            player.sendPluginMessage(getPlugin(), "BungeeCord", b.toByteArray());

            b.close();
            out.close();

        } catch (Exception e) {
            Bukkit.getLogger().severe(e.getMessage());
        }
    }

    private static AestheticMenu getPlugin() {
        return JavaPlugin.getPlugin(AestheticMenu.class);
    }

}
