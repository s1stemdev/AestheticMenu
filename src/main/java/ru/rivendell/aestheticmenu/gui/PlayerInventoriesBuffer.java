package ru.rivendell.aestheticmenu.gui;

import com.google.inject.Singleton;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

@Singleton
public class PlayerInventoriesBuffer {

    @Getter @Setter public HashMap<UUID, ItemStack[]> buffer;

    public PlayerInventoriesBuffer() {
        buffer = new HashMap<>();
    }

}
