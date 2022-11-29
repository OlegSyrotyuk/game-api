package net.stickmix.game.api.util;

import org.bukkit.ChatColor;

public class ChatUtil {

    /**
     * Получить правильное слагательное наклонение в зависимости от количества.
     * Например, 2 -> убийства; 12 -> убийств; 21 -> убийство.
     * @param amount количество.
     * @param uno наклонение для одного (убийство).
     * @param duo наклонение для нескольких (убийства).
     * @param many наклонение для многих (убийств).
     * @return правильное слагательное наклонение в зависимости от количества.
     */
    public static String transformByCount(int amount, String uno, String duo, String many) {
        int mod10 = amount % 10, mod100 = amount % 100;
        if (mod10 == 1 && mod100 != 11) {
            return uno;
        }
        if (mod10 >= 2 && mod10 <= 4 && (mod100 < 10 || mod100 > 20)) {
            return duo;
        }
        return many;
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String colorize(String message, Object... args) {
        return colorize(String.format(message, args));
    }

    public static String prefixed(String prefix, String message) {
        return colorize("&e&l%s &8> %s", prefix, message);
    }

    public static String prefixed(String prefix, String message, Object... args) {
        return prefixed(prefix, String.format(message, args));
    }

}
