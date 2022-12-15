package net.stickmix.game.api.realm;

import java.util.Collection;

public interface RealmManager {

    /**
     * Получить реалм по его айди.
     *
     * @param id айди реалма.
     * @return реалм или null.
     */
    Realm getRealm(String id);

    /**
     * Получить все доступные реалмы.
     *
     * @return коллекция реалмов.
     */
    Collection<Realm> getRealms();

}
