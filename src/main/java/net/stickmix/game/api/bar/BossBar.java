package net.stickmix.game.api.bar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface BossBar extends org.bukkit.boss.BossBar {

    /**
     * Установить имя босс-бару.
     *
     * @param title имя босс-бара.
     */
    void setTitle(String title);

    /**
     * Установить имя босс-бару.
     *
     * @param title имя босс-бара.
     * @param args аргументы форматирования.
     */
    default void setTitle(String title, Object... args) {
        setTitle(String.format(title, args));
    }

    /**
     * Перестать отправлять босс-бар указанному игроку.
     *
     * @param player игрок, которого нужно удалить из босс-бара.
     */
    void removePlayer(Player player);

    /**
     * Отправить босс-бар игроку с нужной локализацией.
     *
     * @param player игрок
     */
    void addPlayer(Player player);

    /**
     * Отправить босс-бар всем спигот-игрокам коллекции.
     *
     * @param players коллекция с игроками.
     */
    default void addSpigotPlayers(Collection<Player> players) {
        players.forEach(this::addPlayer);
    }

    /**
     * Отправить босс-бар всему серверу.
     */
    default void broadcast() {
        Bukkit.getOnlinePlayers().forEach(this::addPlayer);
    }

    /**
     * Получить оставшуюся длительность босс-бара.
     *
     * @return длительность босс-бара.
     */
    default int getDuration() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    default int getTimeLeft(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Получить локализованное имя босс-бара.
     * @return локализованное имя босс-бара.
     */
    String getLocalizedTitle();

}
