package ru.rivendell.aestheticmenu.config.configurations.gui;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.rivendell.aestheticmenu.enums.RequirementType;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RequirementsConfig {

    private RequirementType type;
    private List<RequirementValueConfig> requirements;
    private boolean invert;

    public boolean result(Player player) {

        if(requirements.isEmpty()) return true;

        List<Boolean> results = new ArrayList<>();

        for (RequirementValueConfig requirement : requirements) {
            results.add(requirementResult(requirement, player));
        }

        boolean result = false;

        if(type == RequirementType.ALL) result = isAllTrue(results);
        if(type == RequirementType.ANY) result = isAnyTrue(results);

        if(invert) result = !result;

        return result;
    }

    private boolean requirementResult(RequirementValueConfig requirement, Player player) {
        double first = 0;
        double second = 0;

        try {
            first = Double.parseDouble(PlaceholderAPI.setPlaceholders(player, requirement.getFirst()));
            second = Double.parseDouble(PlaceholderAPI.setPlaceholders(player, requirement.getSecond()));
        } catch (Exception exception) {
            Bukkit.getServer().getLogger().severe(exception.getMessage());
        }

        switch (requirement.getSymbol()) {
            case ">": {
                return first > second;
            }
            case ">=": {
                return first >= second;
            }
            case "<": {
                return first < second;
            }
            case "<=": {
                return first <= second;
            }
            case "==": {
                return first == second;
            }

            default: {
                return false;
            }
        }
    }

    private List<RequirementValueConfig> processPlaceholders(Player player) {
        List<RequirementValueConfig> processed = new ArrayList<>();

        for (RequirementValueConfig requirement : requirements) {
            processed.add(new RequirementValueConfig(
                    PlaceholderAPI.setPlaceholders(player, requirement.getFirst()),
                    requirement.getSymbol(),
                    PlaceholderAPI.setPlaceholders(player, requirement.getSecond())
            ));
        }

        return processed;
    }

    public static boolean isAllTrue(List<Boolean> list) {
        for (Boolean b : list) {
            if (!b) return false;
        }

        return true;
    }

    public static boolean isAnyTrue(List<Boolean> list) {
        for (Boolean b : list) {
            if (b) return true;
        }

        return false;
    }

}
