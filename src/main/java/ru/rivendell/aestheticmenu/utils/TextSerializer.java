package ru.rivendell.aestheticmenu.utils;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.ansi.ANSIComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class TextSerializer {

    public static final MiniMessage mm = MiniMessage.miniMessage();
    public static final LegacyComponentSerializer legacySection = LegacyComponentSerializer.legacySection();
    public static final ANSIComponentSerializer ansi = ANSIComponentSerializer.ansi();

    public static String serializeSection(String s) {
        return legacySection.serialize(mm.deserialize(s));
    }
    public static String serializeAnsi(String s) {
        return ansi.serialize(mm.deserialize(s));
    }

}
