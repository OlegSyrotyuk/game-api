package net.villenium.game.api.user;

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
}
