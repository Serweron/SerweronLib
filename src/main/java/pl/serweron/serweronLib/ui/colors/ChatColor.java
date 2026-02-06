package pl.serweron.serweronLib.ui.colors;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public enum ChatColor {
    BLACK("§0"),
    DARK_BLUE("§1"),
    DARK_GREEN("§2"),
    DARK_AQUA("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    GRAY("§7"),
    DARK_GRAY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    LIGHT_PURPLE("§d"),
    YELLOW("§e"),
    WHITE("§f"),
    RESET("§r");

    @Getter
    private final String code;


    ChatColor(String code) {
        this.code = code;
    }

    @NotNull
    @Override
    public String toString() {
        return code;
    }

    public static ChatColor fromName(String name) {
        for (ChatColor color : values()) {
            if (color.name().equalsIgnoreCase(name)) {
                return color;
            }
        }
        return null;
    }


    public static String translate(char Char, String message) {
        return message.replace(Char, '§');
    }
}
