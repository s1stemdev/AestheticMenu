package ru.rivendell.aestheticmenu.config.configurations.gui.item;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import ru.rivendell.aestheticmenu.config.configurations.gui.requirements.PermissionConfig;
import ru.rivendell.aestheticmenu.config.configurations.gui.requirements.RequirementsConfig;
import ru.rivendell.aestheticmenu.utils.ActionExecutor;

import java.util.List;

@Setter
public class ClickActionConfig {

    @Getter private List<ClickType> clickTypes;
    private List<ActionConfig> actions;
    private RequirementsConfig requirements;
    private List<ActionConfig> onRequirementsCheckFail;
    private List<ActionConfig> noPermission;

    public void execute(Player player) {

        if(actions == null) return;

        if(requirements != null && !requirements.result(player)) {
            if(onRequirementsCheckFail != null) ActionExecutor.executeActions(player, onRequirementsCheckFail);
            return;
        }

        ActionExecutor.executeActions(player, actions);

    }

}
