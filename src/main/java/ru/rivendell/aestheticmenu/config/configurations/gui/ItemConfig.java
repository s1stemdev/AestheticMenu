package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import ru.rivendell.aestheticmenu.config.Configurable;

import java.util.List;

@Getter @Setter
public class ItemConfig {

    private Material material;
    private byte amount;
    private int custommodeldata;
    private String name;
    private List<String> lore;

}
