package net.stickmix.game.api.realm;

import net.stickmix.game.api.user.User;

import java.util.Collection;

public interface Realm {

    /**
     * Получить айди реалма.
     * @return айди реалма.
     */
    String getId();

    /**
     * Получить имя реалма.
     *
     * @return имя реалма.
     */
    String getName();

    /**
     * Получить всех пользователей на данном реалме.
     * @return коллекция пользователей.
     */
    Collection<User> getOnlineUsers();

}
