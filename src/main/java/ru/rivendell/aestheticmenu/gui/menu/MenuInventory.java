package ru.rivendell.aestheticmenu.gui.menu;

import lombok.Getter;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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

    private MiniMessage mm = MiniMessage.miniMessage();
    private LegacyComponentSerializer legacy = LegacyComponentSerializer.legacySection();
    private PlayerInventoriesBuffer playerInventoriesBuffer;

    public MenuInventory(GuiConfig gui, PlayerInventoriesBuffer playerInventoriesBuffer) {
        this.gui = gui;
        this.playerInventoriesBuffer = playerInventoriesBuffer;
    }

    public void openCustomInventory(CustomInventory inventory, Player player) {

        if(getGui().isUsePlayerInventory()) {
            playerInventoriesBuffer.getBuffer().put(player.getUniqueId(), player.getInventory().getContents());
            player.getInventory().clear();

            player.getInventory().setContents(inventory.getPlayerContents());
        }

        player.openInventory(inventory.getChestInventory());

    }

    public CustomInventory build() {
        Inventory inventory = null;

        holder = new MenuHolder(inventory, gui.isUsePlayerInventory());
        inventory = Bukkit.createInventory(holder, gui.getSize(), legacy.serialize(mm.deserialize(gui.getTitle())));

        for(String key : gui.getItems().keySet()) {
            inventory.setItem(Integer.parseInt(key), buildItem(gui.getItems().get(key)));
        }

        if(gui.isUsePlayerInventory()) {

            return new CustomInventory(inventory, buildPlayerContents());
        }

        return new CustomInventory(inventory);
    }

    public ItemStack[] buildPlayerContents() {
        ItemStack[] contents = new ItemStack[41];

        for(String key : gui.getPlayerInvItems().keySet()) {
            contents[Integer.parseInt(key)] = buildItem(gui.getPlayerInvItems().get(key));
        }

        for (int i = 0; i < contents.length; i++) {
            if(contents[i] == null) contents[i] = new ItemStack(Material.AIR);
        }

        return contents;
    }

    private ItemStack buildItem(ItemConfig itemConfig) {
        ItemStack item = new ItemStack(itemConfig.getMaterial());

        item.setAmount(itemConfig.getAmount());

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(legacy.serialize(mm.deserialize(itemConfig.getName())));
        meta.setLore(serializeLore(itemConfig.getLore()));

        item.setItemMeta(meta);
        return item;
    }

    private List<String> serializeLore(List<String> lore) {
        List<String> serializedLore = new ArrayList<>();

        for (String s : lore) {
            serializedLore.add(legacy.serialize(mm.deserialize(s)));
        }

        return serializedLore;
    }

}
