package net.stickmix.game.api.user;

import org.bukkit.entity.Player;

import java.util.function.Consumer;

public interface UserManager {

    /**
     * Получить пользователя по его имени.
     *
     * @param name имя пользователя.
     * @return пользователь.
     */
    User get(String name);

    default User get(Player player) {
        return get(player.getName());
    }

    /**
     * Удалить пользователя из кэша если он оффлайн на этом сервере.
     *
     * @param name имя пользователя.
     */
    void invalidateIfOffline(String name);

    default void invalidateIfOffline(Player player) {
        invalidateIfOffline(player.getName());
    }

    default void invalidateIfOffline(User user) {
        invalidateIfOffline(user.getName());
    }

    /**
     * Метод получит объект пользователя и выполнит действие описанное в userConsumer.
     * Если пользователь оффлайн, то будет выполнен метод invalidateIfOffline.
     *
     * @param name         имя пользователя.
     * @param userConsumer действие которое необходимо выполнить.
     */
    default void getUserDoAndInvalidateIfOffline(String name, Consumer<User> userConsumer) {
        User user = get(name);
        if (user == null) {
            return;
        }
        userConsumer.accept(user);
        invalidateIfOffline(user);
    }

    /**
     * @param player       bukkit игрок.
     * @param userConsumer действие которое необходимо выполнить.
     * @see UserManager#getUserDoAndInvalidateIfOffline(String, Consumer)
     */
    default void getUserDoAndInvalidateIfOffline(Player player, Consumer<User> userConsumer) {
        getUserDoAndInvalidateIfOffline(player.getName(), userConsumer);
    }
}
