package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Setter;
import org.bukkit.entity.Player;
import ru.rivendell.aestheticmenu.config.configurations.gui.item.ActionConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.requirements.RequirementsConfig;
import ru.rivendell.aestheticmenu.utils.ActionExecutor;

import java.util.List;

@Setter
public class GuiOpenRequirementsConfig {

    private RequirementsConfig requirements;
    private List<ActionConfig> onFail;

    public boolean shouldOpen(Player player) {
        boolean result = requirements.result(player);

        if(!result && onFail != null && !onFail.isEmpty()) {
            ActionExecutor.executeActions(player, onFail);
        }

        return result;
    }

}
