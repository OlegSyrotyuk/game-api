package net.villenium.game.api.bar;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;

public interface Bar {

    /**
     * @see Bar#createDefaultBar(String, BarColor, BarStyle, BarFlag...)
     */
    default BossBar createDefaultBar(String title, BarColor color, Object... args) {
        return createDefaultBar(String.format(title, args), color, BarStyle.SOLID);
    }

    /**
     * @see Bar#createDefaultBar(String, BarColor, BarStyle, BarFlag...)
     */
    default BossBar createDefaultBar(String title, BarFlag... flags) {
        return createDefaultBar(title, BarColor.PURPLE, BarStyle.SOLID, flags);
    }

    /**
     * @see Bar#createDefaultBar(String, BarColor, BarStyle, BarFlag...)
     */
    default BossBar createDefaultBar(String title, BarStyle style, BarFlag... flags) {
        return createDefaultBar(title, BarColor.PURPLE, style, flags);
    }

    /**
     * @see Bar#createDefaultBar(String, BarColor, BarStyle, BarFlag...)
     */
    default BossBar createDefaultBar(String title, BarColor color, BarFlag... flags) {
        return createDefaultBar(title, color, BarStyle.SOLID, flags);
    }


    /**
     * @see Bar#createDefaultBar(String, BarColor, BarStyle, BarFlag...)
     */
    BossBar createDefaultBar(String title, BarColor color, BarStyle style, BarFlag... flags);

    /**
     * @see Bar#createExpiredBar(String, int, BarColor, BarStyle, BarFlag...)
     */
    default BossBar createExpiredBar(String title, int duration, BarColor color, Object... args) {
        return createExpiredBar(String.format(title, args), duration, color, BarStyle.SOLID);
    }

    /**
     * Создать и получить босс-бар с стандартными настройками и заданными временем и именем,
     * который пропадет через определенное время.
     *
     * @param title    текст, который будет находиться над баром. Все & будут заменены на цветовые коды.
     * @param duration время жизни бара в секундах.
     * @param flags    флаги, которые будут применены к бару.
     * @return мапу с барами для всех языков.
     */
    default BossBar createExpiredBar(String title, int duration, BarFlag... flags) {
        return createExpiredBar(title, duration, BarColor.PURPLE, BarStyle.SOLID, flags);
    }

    /**
     * Создать и получить босс-бар с собственными настройками,
     * который пропадет через определенное время.
     *
     * @param title    текст, который будет находиться над баром. Все & будут заменены на цветовые коды.
     * @param duration время жизни бара в секундах.
     * @param style    стиль деления бара.
     * @param flags    флаги, которые будут применены к бару.
     * @return мапу с барами для всех языков.
     */
    default BossBar createExpiredBar(String title, int duration, BarStyle style, BarFlag... flags) {
        return createExpiredBar(title, duration, BarColor.PURPLE, style, flags);
    }

    /**
     * Создать и получить босс-бар с собственными настройками,
     * который пропадет через определенное время.
     *
     * @param title    текст, который будет находиться над баром. Все & будут заменены на цветовые коды.
     * @param duration время жизни бара в секундах.
     * @param color    цвет бара.
     * @param flags    флаги, которые будут применены к бару.
     * @return мапу с барами для всех языков.
     */
    default BossBar createExpiredBar(String title, int duration, BarColor color, BarFlag... flags) {
        return createExpiredBar(title, duration, color, BarStyle.SOLID, flags);
    }

    /**
     * Создать и получить босс-бар с собственными настройками,
     * который пропадет через определенное время.
     *
     * @param title    текст, который будет находиться над баром. Все & будут заменены на цветовые коды.
     * @param duration время жизни бара в секундах.
     * @param color    цвет бара.
     * @param style    стиль деления бара.
     * @param flags    флаги, которые будут применены к бару.
     * @return мапу с барами для всех языков.
     */
    BossBar createExpiredBar(String title, int duration, BarColor color, BarStyle style, BarFlag... flags);

}
