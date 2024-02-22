package ru.rivendell.aestheticmenu.config.configurations.gui.requirements;

import lombok.Getter;
import org.bukkit.entity.Player;
import ru.rivendell.aestheticmenu.enums.RequirementType;
import ru.rivendell.aestheticmenu.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PermissionConfig {

    private RequirementType type;
    private List<String> permissions;
    private boolean invert;

    public boolean result(Player player) {
        List<Boolean> results = new ArrayList<>();

        if(permissions == null || permissions.isEmpty()) return true;

        boolean result = false;

        for (int i = 0; i < permissions.size(); i++) {
            results.set(i, player.hasPermission(permissions.get(i)));
        }

        if(type == RequirementType.ALL) result = ListUtils.isAllTrue(results);
        if(type == RequirementType.ANY) result = ListUtils.isAnyTrue(results);

        if(invert) result = !result;

        return result;

    }



}
