package ru.rivendell.aestheticmenu.gui.menu;

import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.rivendell.aestheticmenu.config.configurations.gui.GuiConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.ItemConfig;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;

import java.util.ArrayList;
import java.util.List;

public class MenuInventory {

    @Getter private GuiConfig gui;

    @Getter private MenuHolder holder;

    private MiniMessage mm;
    private PlainTextComponentSerializer plain;

    public MenuInventory(GuiConfig gui, MiniMessage mm, PlainTextComponentSerializer plain) {
        this.gui = gui;
        this.mm = mm;
        this.plain = plain;
    }

    public Inventory build() {
        Inventory inventory = null;

        holder = new MenuHolder(inventory);
        inventory = Bukkit.createInventory(holder, gui.getSize(), gui.getTitle());

        for(String key : gui.getItems().keySet()) {
            inventory.setItem(Integer.parseInt(key), buildItem(gui.getItems().get(key)));
        }

        return inventory;
    }

    private ItemStack buildItem(ItemConfig itemConfig) {
        ItemStack item = new ItemStack(itemConfig.getMaterial());

        item.setAmount(itemConfig.getAmount());

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(plain.serialize(mm.deserialize(itemConfig.getName())));
        meta.setLore(serializeLore(itemConfig.getLore()));

        item.setItemMeta(meta);
        return item;
    }

    private List<String> serializeLore(List<String> lore) {
        List<String> serializedLore = new ArrayList<>();

        for (String s : lore) {
            serializedLore.add(plain.serialize(mm.deserialize(s)));
        }

        return serializedLore;
    }

}
