package net.stickmix.game.api;

import org.bukkit.entity.Player;

public interface Title {

    /**
     * Отправить тайтл игроку.
     * @param player игрок.
     * @param titleType тип тайтла.
     * @param message сообщение тайтла.
     */
    void sendTitle(Player player, TitleType titleType, String message);

    /**
     * Отправить тайтл игроку.
     * @param player игрок.
     * @param titleType тип тайтла.
     * @param message ключ локализованного сообщения тайтла.
     * @param args аргументы форматирования локализованного сообщения тайтла.
     */
    default void sendTitle(Player player, TitleType titleType, String message, Object... args) {
        sendTitle(player, titleType, String.format(message, args));
    }

    enum TitleType {
        TITLE,
        SUBTITLE,
        SMALLEST_TITLE
    }

}
