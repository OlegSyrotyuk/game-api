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

    /**
     * Получить все реалмы айди которых начинается на section.
     *
     * @param section секция реалмов.
     * @return секция реалмов.
     */
    Collection<Realm> getRealmSection(String section);

    /**
     * Вывести глобальное сообщение на секции реалмов.
     *
     * @param section секция реалмов.
     * @param message сообщение.
     * @param args    аргументы.
     */
    void broadcastMessageToRealmSection(String section, String message, Object... args);

    /**
     * Получить текущий реалм.
     *
     * @return текущий реалм.
     */
    Realm getCurrentRealm();
}
