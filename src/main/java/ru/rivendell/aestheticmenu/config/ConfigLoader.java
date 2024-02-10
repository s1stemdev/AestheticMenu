package ru.rivendell.aestheticmenu.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.rivendell.aestheticmenu.AestheticMenu;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class ConfigLoader {

    @Inject private AestheticMenu plugin;

    public ConfigLoader() { }

    public <T extends Configurable> void save(T clazz) {
        Gson g = Gson();
        String json = g.toJson(clazz);

        File file = new File(plugin.getDataFolder(), clazz.getName() + ".json");

        try {
            Path path = file.toPath();
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.write(path, List.of(json),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            plugin.getLog().severe("IOException");
        }
    }

    public <T extends Configurable> T load(String name, Class<T> clazz) {
        Gson g = Gson();
        File jsonConfig = new File(plugin.getDataFolder(), name + ".json");

        try {
            byte[] bytes = Files.readAllBytes(jsonConfig.toPath());
            return g.fromJson(new String(bytes, StandardCharsets.UTF_8), clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Gson Gson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.STATIC)
                //.registerTypeAdapter(Location.class, new LocationAdapter())
                .create();
    }

}
