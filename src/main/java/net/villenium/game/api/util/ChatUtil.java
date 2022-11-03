package net.villenium.game.api.util;

public interface ChatUtil {

    /**
     * Получить правильное слагательное наклонение в зависимости от количества.
     * Например, 2 -> убийства; 12 -> убийств; 21 -> убийство.
     * @param amount количество.
     * @param uno наклонение для одного (убийство).
     * @param duo наклонение для нескольких (убийства).
     * @param many наклонение для многих (убийств).
     * @return правильное слагательное наклонение в зависимости от количества.
     */
    default String transformByCount(int amount, String uno, String duo, String many) {
        int mod10 = amount % 10, mod100 = amount % 100;
        if (mod10 == 1 && mod100 != 11) {
            return uno;
        }
        if (mod10 >= 2 && mod10 <= 4 && (mod100 < 10 || mod100 > 20)) {
            return duo;
        }
        return many;
    }

    String colorize(String message);

    default String colorize(String message, Object... args) {
        return colorize(String.format(message, args));
    }

    String prefixed(String prefix, String message);

    default String prefixed(String prefix, String message, Object... args) {
        return prefixed(prefix, String.format(message, args));
    }

}
