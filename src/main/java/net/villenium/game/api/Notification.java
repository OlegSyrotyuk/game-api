package net.villenium.game.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface Notification {

    /**
     * Отправить нотификацию всем игрокам сервера.
     * К содержанию применяется замена цветовых кодов с &.
     *
     * @param icon  предмет который будет отображен слева от содержания.
     * @param text  содержание объявления.
     * @param frame тип рамки объявления.
     */
    void broadcast(Material icon, String text, FrameType frame);

    /**
     * Отправить нотификацию всем указанным игрокам.
     * К содержанию применяется замена цветовых кодов с &.
     *
     * @param players список игроков, которым отправить объявление.
     * @param icon    предмет который будет отображен слева от содержания.
     * @param text    содержание объявления.
     * @param frame   тип рамки объявления.
     */
    void sendToSpigotPlayers(Collection<Player> players, Material icon, String text, FrameType frame);

    /**
     * Отправить нотификацию указанному игроку.
     * К содержанию применяется замена цветовых кодов с &.
     *
     * @param player игрок, которому отправить объявление.
     * @param icon   предмет который будет отображен слева от содержания.
     * @param text   содержание объявления.
     * @param frame  тип рамки объявления.
     */
    void send(Player player, Material icon, String text, FrameType frame);

    /**
     * Отправить нотификацию указанному игроку.
     * К содержанию применяется замена цветовых кодов с &.
     *
     * @param player игрок, которому отправить объявление.
     * @param icon   предмет который будет отображен слева от содержания.
     * @param text   содержание объявления.
     * @param frame  тип рамки объявления.
     */
    default void send(String player, Material icon, String text, FrameType frame) {
        send(Bukkit.getPlayerExact(player), icon, text, frame);
    }

    enum FrameType {
        TASK,
        CHALLENGE,
        GOAL
    }
}
