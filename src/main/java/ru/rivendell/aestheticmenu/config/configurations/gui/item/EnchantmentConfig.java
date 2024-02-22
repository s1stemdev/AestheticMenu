package ru.rivendell.aestheticmenu.config.configurations.gui.item;

import lombok.Getter;
import lombok.Setter;

@Setter
public class EnchantmentConfig {

    @Getter private String enchant;
    private int level;

    public int getLevel() {
        if(level == 0) return 1;

        return level;
    }

}
