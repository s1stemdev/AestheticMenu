package ru.rivendell.aestheticmenu.config.configurations;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import ru.rivendell.aestheticmenu.config.Configurable;

@Getter @Setter
public class ItemConfig {

    private Material material;
    private byte amount;
    private int custommodeldata;

}
