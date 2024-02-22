package ru.rivendell.aestheticmenu.utils;

import java.util.List;

public class ListUtils {

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
