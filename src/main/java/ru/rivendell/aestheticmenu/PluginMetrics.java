package ru.rivendell.aestheticmenu;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;

@Singleton
public class PluginMetrics {

    @Inject private AestheticMenu plugin;

    private final int pluginId = 20966;
    private Metrics metrics;

    public PluginMetrics() { }

    public void setupMetrics() {
        metrics = new Metrics(plugin, pluginId);

        metrics.addCustomChart(new SimplePie("plugin_version", () -> {
            return plugin.getDescription().getVersion();
        }));

        metrics.addCustomChart(new SimplePie("server_version", () -> {
            return Bukkit.getVersion();
        }));
    }

}
