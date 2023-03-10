package net.villenium.game.api.party;

public interface PartyManager {

    /**
     * Получить тусовку по айди.
     *
     * @param id айди тусовки.
     * @return тусовка.
     */
    Party getParty(String id);

    /**
     * Получить тусовку по её участнику.
     *
     * @param member имя участника.
     * @return объект тусовки или null.
     */
    Party getPartyByMember(String member);

    /**
     * Проверить являются ли пользователи участниками одной тусовки.
     *
     * @param firstMember  имя первого пользователя.
     * @param secondMember имя второго пользователя.
     * @return результат.
     */
    boolean isInParty(String firstMember, String secondMember);
}
