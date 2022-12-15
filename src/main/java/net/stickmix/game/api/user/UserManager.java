package net.stickmix.game.api.user;

import org.bukkit.entity.Player;

public interface UserManager {

    /**
     * Получить пользователя по его имени.
     * @param name имя пользователя.
     * @return пользователь.
     */
    User get(String name);

    default User get(Player player) {
        return get(player.getName());
    }

    /**
     * Удалить пользователя из кэша если он оффлайн на этом сервере.
     * @param name имя пользователя.
     */
    void invalidateIfOffline(String name);

    default void invalidateIfOffline(Player player) {
        invalidateIfOffline(player.getName());
    }

    default void invalidateIfOffline(User user) {
        invalidateIfOffline(user.getName());
    }
}
