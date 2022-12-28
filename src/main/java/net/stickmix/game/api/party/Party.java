package net.stickmix.game.api.party;

import net.stickmix.game.api.GameApi;
import net.stickmix.game.api.user.User;

import java.util.Collection;
import java.util.stream.Collectors;

public interface Party {

    /**
     * Проверить существует ли эта тусовка.
     * @return результат.
     */
    boolean isExists();

    /**
     * Получить айди тусовки.
     * @return айди тусовки.
     */
    String getId();

    /**
     * Получить имя лидера тусовки.
     * @return имя лидера.
     */
    String getLeaderName();

    /**
     * Получить список из имен участников тусовки.
     * @return список имен.
     */
    Collection<String> getMembersNames();

    /**
     * Получить пользователя лидера тусовки.
     * @return пользователь лидер.
     */
    default User getLeader() {
        return GameApi.getUserManager().get(getLeaderName());
    }

    /**
     * Получить список из пользователей участников тусовки.
     * @return список из пользователей.
     */
    default Collection<User> getMembers() {
        return getMembersNames().stream().map(GameApi.getUserManager()::get).collect(Collectors.toList());
    }

    /**
     * Получить максимальное количество участников тусовки.
     * @return максимальное количество участников.
     */
    int getMaxMemberCount();

}
