package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import ru.rivendell.aestheticmenu.config.Configurable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
public class ItemConfig {

    private Material material;
    @Getter private String headValue;
    private byte amount;
    @Getter private int custommodeldata;
    private String name;
    private List<String> lore;
    private List<ItemFlag> flags;
    private List<EnchantmentConfig> enchantments;
    private ItemContainerConfig data;

    public Material getMaterial() {
        if(material == null) return Material.STONE;

        return material;
    }

    public byte getAmount() {
        if(amount == 0) return 1;

        return amount;
    }

    public String getName() {

        if(name == null) return processItemName();

        return name;
    }

    public List<String> getLore() {
        if(lore == null) return new ArrayList<>();

        return lore;
    }

    public List<ItemFlag> getFlags() {
        if(flags == null) return new ArrayList<>();

        return flags;
    }

    public List<EnchantmentConfig> getEnchantments() {
        if(enchantments == null) return new ArrayList<>();

        return enchantments;
    }

    public ItemContainerConfig getData() {
        if(data == null) return new ItemContainerConfig();

        return data;
    }

    private String processItemName() {
        String name = material.toString();

        name = name.replaceAll("_", " ");
        name = name.toLowerCase();
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        return name;
    }
}
