package ru.rivendell.aestheticmenu.gui.menu;

import com.google.gson.Gson;
import lombok.Getter;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import revxrsal.commands.annotation.Command;
import ru.rivendell.aestheticmenu.AestheticMenu;
import ru.rivendell.aestheticmenu.config.configurations.gui.EnchantmentConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.GuiConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.ItemConfig;
import ru.rivendell.aestheticmenu.gui.PlayerInventoriesBuffer;
import ru.rivendell.aestheticmenu.utils.SkullCreator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MenuInventory {

    @Getter private GuiConfig gui;

    @Getter private MenuHolder holder;

    private MiniMessage mm = MiniMessage.miniMessage();
    private LegacyComponentSerializer legacy = LegacyComponentSerializer.legacySection();
    private PlayerInventoriesBuffer playerInventoriesBuffer;
    private Gson serializer;

    public MenuInventory(GuiConfig gui, PlayerInventoriesBuffer playerInventoriesBuffer) {
        this.gui = gui;
        this.playerInventoriesBuffer = playerInventoriesBuffer;

        serializer = new Gson();
    }

    public void openCustomInventory(CustomInventory inventory, Player player) {

        if(getGui().isUsePlayerInventory()) {
            playerInventoriesBuffer.getBuffer().put(player.getUniqueId(), player.getInventory().getContents());
            player.getInventory().clear();

            player.getInventory().setContents(inventory.getPlayerContents());
        }

        player.openInventory(inventory.getChestInventory());

    }

    public CustomInventory build(Player player) {
        Inventory inventory = null;

        holder = new MenuHolder(inventory, gui.isUsePlayerInventory());
        inventory = Bukkit.createInventory(holder, gui.getSize(), legacy.serialize(mm.deserialize(PlaceholderAPI.setPlaceholders(player, gui.getTitle()))));

        for(String key : gui.getItems().keySet()) {
            inventory.setItem(Integer.parseInt(key), buildItem(gui.getItems().get(key), player));
        }

        if(gui.isUsePlayerInventory()) {

            return new CustomInventory(inventory, buildPlayerContents(player));
        }

        return new CustomInventory(inventory);
    }

    public ItemStack[] buildPlayerContents(Player player) {
        ItemStack[] contents = new ItemStack[41];

        for(String key : gui.getPlayerInvItems().keySet()) {
            contents[Integer.parseInt(key)] = buildItem(gui.getPlayerInvItems().get(key), player);
        }

        for (int i = 0; i < contents.length; i++) {
            if(contents[i] == null) contents[i] = new ItemStack(Material.AIR);
        }

        return contents;
    }

    private ItemStack buildItem(ItemConfig itemConfig, Player player) {
        ItemStack item;

        if(itemConfig.getMaterial() == Material.PLAYER_HEAD && itemConfig.getHeadValue() != null) {
            item = SkullCreator.itemFromBase64(itemConfig.getHeadValue());
        }
        else {
            item = new ItemStack(itemConfig.getMaterial());
        }

        item.setAmount(itemConfig.getAmount());

        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(legacy.serialize(mm.deserialize(PlaceholderAPI.setPlaceholders(player, itemConfig.getName()))));
        meta.setLore(serializeLore(itemConfig.getLore(), player));

        for (EnchantmentConfig enchant : itemConfig.getEnchantments()) {
            try {
                Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchant.getEnchant().toLowerCase()));
                meta.addEnchant(enchantment, enchant.getLevel(), true);
            } catch (Exception e) {
                Bukkit.getServer().getLogger().severe(e.getMessage());
            }
        }

        for (ItemFlag flag : itemConfig.getFlags()) {
            meta.addItemFlags(flag);
        }

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(AestheticMenu.COMMANDS_KEY, PersistentDataType.STRING,
                serializer.toJson(itemConfig.getData())
        );

        item.setItemMeta(meta);
        return item;
    }

    private List<String> serializeLore(List<String> lore, Player player) {
        List<String> serializedLore = new ArrayList<>();

        for (String s : lore) {
            serializedLore.add(legacy.serialize(mm.deserialize(PlaceholderAPI.setPlaceholders(player, s))));
        }

        return serializedLore;
    }

}
