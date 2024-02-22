package ru.rivendell.aestheticmenu.config.configurations.gui.item;

import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import ru.rivendell.aestheticmenu.config.configurations.gui.requirements.RequirementsConfig;
import ru.rivendell.aestheticmenu.utils.ActionExecutor;

import java.util.HashMap;
import java.util.List;

@Setter
@Deprecated
public class ItemContainerConfig {

    private HashMap<ClickType, List<ActionConfig>> actions;
    private HashMap<ClickType, RequirementsConfig> requirements;
    private HashMap<ClickType, List<ActionConfig>> failRequirementsActions;
    private HashMap<ClickType, String> permissions;
    private HashMap<ClickType, List<ActionConfig>> failPermissionsActions;


    public boolean execute(Player player, ClickType type) {

        if(permissions.get(type) != null && !player.hasPermission(permissions.get(type))) {
            ActionExecutor.executeActions(player, failPermissionsActions.get(type));
            return false;
        }


        if(requirements.get(type) != null & !requirements.get(type).result(player)) {
            ActionExecutor.executeActions(player, failRequirementsActions.get(type));
            return false;
        }

        ActionExecutor.executeActions(player, actions.get(type));
        return true;
    }

    public HashMap<ClickType, List<ActionConfig>> getActions() {
        if(actions == null) return new HashMap<>();
        return actions;
    }

    public HashMap<ClickType, RequirementsConfig> getRequirements() {
        if(requirements == null) return new HashMap<>();
        return requirements;
    }

    public HashMap<ClickType, List<ActionConfig>> getFailRequirementsActions() {
        if(failRequirementsActions == null) return new HashMap<>();
        return failRequirementsActions;
    }

    public HashMap<ClickType, String> getPermissions() {
        if(permissions == null) return new HashMap<>();
        return permissions;
    }

    public HashMap<ClickType, List<ActionConfig>> getFailPermissionsActions() {
        if(failPermissionsActions == null) return new HashMap<>();
        return failPermissionsActions;
    }
}
