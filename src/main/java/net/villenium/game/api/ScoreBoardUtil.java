package net.villenium.game.api;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface ScoreBoardUtil {

    /**
     * Включить на постоянной основе авто-обновление названия скорборда с указанной гаммой анимации.
     *
     * @param gamma гамма анимаци.
     */
    void enableTitleAnimationGamma(String title, AnimationGamma gamma);

    /**
     * Создать скорборд с заданным названием/обновить его название для указанного игрока.
     *
     * @param player игрок.
     * @param title  название скорборда (цветовые коды автоматически заменятся).
     */
    default void updateTitle(Player player, String title) {
        updateTitle(Collections.singleton(player), title);
    }

    /**
     * Создать скорборд с заданным названием/обновить его название для указанного игрока.
     *
     * @param player игрок.
     * @param title  название скорборда (цветовые коды автоматически заменятся).
     * @param args   аргументы форматирования текста строки.
     */
    default void updateTitle(Player player, String title, Object... args) {
        updateTitle(player, String.format(title, args));
    }

    /**
     * Создать скорборд с заданным названием/обновить его название для указанных игроков.
     *
     * @param players игроки.
     * @param title   название скорборда (цветовые коды автоматически заменятся).
     */
    void updateTitle(Collection<Player> players, String title);

    /**
     * Создать скорборд с заданным названием/обновить его название для указанных игроков.
     *
     * @param players игроки.
     * @param title   название скорборда (цветовые коды автоматически заменятся).
     * @param args    аргументы форматирования текста строки.
     */
    default void updateTitle(Collection<Player> players, String title, Object... args) {
        updateTitle(players, String.format(title, args));
    }

    /**
     * Отправить указанному игроку строчку в скорборде.
     *
     * @param player   игрок.
     * @param priority номер строки (1-16 включительно).
     * @param text     текст строки (цветовые коды автоматически заменятся).
     */
    default void send(Player player, int priority, String text) {
        send(Collections.singleton(player), priority, text);
    }

    /**
     * Отправить указанному игроку строчку в скорборде.
     *
     * @param player   игрок.
     * @param priority номер строки (1-16 включительно).
     * @param text     текст строки (цветовые коды автоматически заменятся).
     * @param args     аргументы форматирования текста строки.
     */
    default void send(Player player, int priority, String text, Object... args) {
        send(player, priority, String.format(text, args));
    }

    /**
     * Отправить указанному игроку список из указанных строк в скорборде.
     *
     * @param player игрок.
     * @param text   строки текста.
     */
    default void send(Player player, List<String> text) {
        removeAll(player);
        for (int i = 0; i < text.size(); ++i) {
            send(player, text.size() - i, text.get(i));
        }
    }

    /**
     * Отправить указанным игрокам строчку в скорборде.
     *
     * @param players  игроки.
     * @param priority номер строки (1-16 включительно).
     * @param text     текст строки (цветовые коды автоматически заменятся).
     */
    void send(Collection<Player> players, int priority, String text);

    /**
     * Отправить указанным игрокам строчку в скорборде.
     *
     * @param players  игроки.
     * @param priority номер строки (1-16 включительно).
     * @param text     текст строки (цветовые коды автоматически заменятся).
     * @param args     аргументы форматирования текста строки.
     */
    default void send(Collection<Player> players, int priority, String text, Object... args) {
        send(players, priority, String.format(text, args));
    }

    /**
     * Удалить у указанного игрока строку с определенным номером.
     *
     * @param player   игрок.
     * @param priority номер строки (1-16 включительно).
     */
    default void remove(Player player, int priority) {
        remove(Collections.singleton(player), priority);
    }

    /**
     * Удалить у указанных игроков строку с определенным номером.
     *
     * @param players  игроки.
     * @param priority номер строки (1-16 включительно).
     */
    void remove(Collection<Player> players, int priority);

    /**
     * Удалить у указанного игрока все строки из скорборда.
     *
     * @param player игрок.
     */
    default void removeAll(Player player) {
        removeAll(Collections.singleton(player));
    }

    /**
     * Удалить у указанных игроков все строки из скорборда.
     *
     * @param players игроки.
     */
    void removeAll(Collection<Player> players);

    @AllArgsConstructor
    enum AnimationGamma {

        GOLD("&f", "&e", "&6"),
        AQUA("&f", "&b", "&3"),
        PURPLE("&f", "&d", "&5"),
        RED("&f", "&c", "&c"),
        GREEN("&f", "&a", "&2");

        public final String cc1, cc2, cc3;
    }

}
