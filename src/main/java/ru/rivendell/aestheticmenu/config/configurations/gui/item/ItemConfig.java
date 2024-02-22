package ru.rivendell.aestheticmenu.config.configurations.gui.item;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.ClickActionConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.EnchantmentConfig;

import java.util.ArrayList;
import java.util.List;

@Setter
public class ItemConfig {

    private Material material;
    @Getter private String headValue;
    private byte amount;
    @Getter private int custommodeldata;
    @Getter private String name;
    private List<String> lore;
    private List<ItemFlag> flags;
    private List<EnchantmentConfig> enchantments;
    private List<ClickActionConfig> clickActions;
    private int slot;
    private List<Integer> slots;

    public Material getMaterial() {
        if(material == null) return Material.STONE;

        return material;
    }

    public byte getAmount() {
        if(amount == 0) return 1;

        return amount;
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

    public List<ClickActionConfig> getClickActions() {
        if(clickActions == null) return new ArrayList<>();

        return clickActions;
    }

    public List<Integer> getSlots() {
        if(slots == null || slots.isEmpty()) {
            slots = new ArrayList<>();
            slots.add(slot);
        }

        return slots;
    }
}
