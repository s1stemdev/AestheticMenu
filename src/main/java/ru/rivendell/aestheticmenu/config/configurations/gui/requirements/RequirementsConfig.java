package ru.rivendell.aestheticmenu.config.configurations.gui.requirements;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.rivendell.aestheticmenu.enums.RequirementType;
import ru.rivendell.aestheticmenu.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RequirementsConfig {

    private RequirementType type;
    private List<RequirementValueConfig> requirements;
    private boolean invert;

    public RequirementsConfig() {
        type = RequirementType.NONE;
        requirements = new ArrayList<>();
        invert = false;
    }

    public boolean result(Player player) {

        if(requirements == null || requirements.isEmpty()) return true;

        List<Boolean> results = new ArrayList<>();

        for (RequirementValueConfig requirement : requirements) {
            results.add(requirementResult(requirement, player));
        }

        boolean result = false;

        if(type == RequirementType.ALL) result = ListUtils.isAllTrue(results);
        if(type == RequirementType.ANY) result = ListUtils.isAnyTrue(results);

        if(invert) result = !result;

        return result;
    }

    private boolean requirementResult(RequirementValueConfig requirement, Player player) {
        try {
            switch (requirement.getType()) {
                case NUM: {
                    return numberResult(Double.parseDouble(PlaceholderAPI.setPlaceholders(player, requirement.getFirst())), Double.parseDouble(PlaceholderAPI.setPlaceholders(player, requirement.getSecond())), requirement.getSymbol(), player);
                }
                case STR: {
                    return stringResult(requirement.getFirst(), requirement.getSecond(), requirement.getSymbol(), player);
                }
                case BOOL: {
                    return Boolean.parseBoolean(PlaceholderAPI.setPlaceholders(player, requirement.getValue()));
                }
                case PERMISSION: {
                    return player.hasPermission(PlaceholderAPI.setPlaceholders(player, requirement.getValue()));
                }
                default: {
                    return false;
                }
            }
        } catch (Exception e) {
            Bukkit.getLogger().severe(e.getMessage());
            return false;
        }
    }

    private boolean stringResult(String s, String s2, String symb, Player player) {
        if(symb.equals("==")) return PlaceholderAPI.setPlaceholders(player, s).equals(PlaceholderAPI.setPlaceholders(player, s2));
        if(symb.equals("!=")) return !PlaceholderAPI.setPlaceholders(player, s).equals(PlaceholderAPI.setPlaceholders(player, s2));

        return false;
    }

    private boolean numberResult(double first, double second, String symb, Player player) {

        switch (symb) {
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
                    requirement.getType(),
                    PlaceholderAPI.setPlaceholders(player, requirement.getFirst()),
                    requirement.getSymbol(),
                    PlaceholderAPI.setPlaceholders(player, requirement.getSecond())
            ));
        }

        return processed;
    }

}
