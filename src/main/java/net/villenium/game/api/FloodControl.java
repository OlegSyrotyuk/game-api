package net.villenium.game.api;

import net.villenium.game.api.user.User;

import java.util.concurrent.TimeUnit;

/**
 * Можно не заботиться об инвалидации и утечках памяти: "протухшие" флуд-ключи
 * автоматически чистятся раз в 5 минут.
 */
public interface FloodControl {

    /**
     * Проверить, возможно ли действие по флуд-контролю.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param floodKey         ключ действия.
     * @param addition         количество добавляемых действий.
     * @param limit            максимальное количество действий, которое может быть совершено за данное время.
     * @param cooldown         длительность, за которую можно совершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return true, если всё хорошо; false, если действие уже уперлось в лимит.
     * @throws IllegalArgumentException если addition < 0, limit < addition или cooldown < 100ms.
     */
    boolean checkAndAdd(String floodKey, int addition, int limit, long cooldown, TimeUnit cooldownTimeUnit);

    /**
     * Проверить, доступно ли действие игроку по флуд-контролю.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param playerName       ник игрока.
     * @param floodKey         ключ действия.
     * @param addition         количество добавляемых действий.
     * @param limit            максимальное количество действий, которое игрок может совершить за данное время.
     * @param cooldown         длительность, за которую игрок может завершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return true, если всё хорошо; false, если игрок уже уперся в лимит.
     * @throws IllegalArgumentException если addition < 0, limit < addition или cooldown < 100ms.
     */
    default boolean checkAndAdd(String playerName, String floodKey, int addition, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAdd(playerName.toLowerCase() + "." + floodKey, addition, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * Проверить, доступно ли действие игроку по флуд-контролю.
     * При вызове считается, что игрок выполнил +1 действие.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param playerName       ник игрока.
     * @param floodKey         ключ действия.
     * @param limit            максимальное количество действий, которое игрок может совершить за данное время.
     * @param cooldown         длительность, за которую игрок может завершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return true, если всё хорошо; false, если игрок уже уперся в лимит.
     * @throws IllegalArgumentException если limit < 1 или cooldown < 100ms.
     */
    default boolean checkAndAdd(String playerName, String floodKey, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAdd(playerName, floodKey, 1, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * Проверить, доступно ли действие игроку по флуд-контролю.
     * При вызове считается, что игрок выполнил +1 действие, при этом лимит - тоже всего 1
     * действие за отведенное время.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param playerName       ник игрока.
     * @param floodKey         ключ действия.
     * @param cooldown         длительность, за которую игрок может завершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return true, если всё хорошо; false, если игрок уже уперся в лимит.
     * @throws IllegalArgumentException если cooldown < 100ms.
     */
    default boolean checkAndAdd(String playerName, String floodKey, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAdd(playerName, floodKey, 1, 1, cooldown, cooldownTimeUnit);
    }

    /**
     * @see FloodControl#checkAndAdd(String, String, int, int, long, TimeUnit)
     */
    default boolean checkAndAdd(User user, String floodKey, int addition, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAdd(user.getName(), floodKey, addition, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * @see FloodControl#checkAndAdd(String, String, int, long, TimeUnit)
     */
    default boolean checkAndAdd(User user, String floodKey, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAdd(user.getName(), floodKey, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * @see FloodControl#checkAndAdd(String, String, long, TimeUnit)
     */
    default boolean checkAndAdd(User user, String floodKey, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAdd(user.getName(), floodKey, cooldown, cooldownTimeUnit);
    }

    /**
     * Проверить, возможно ли действие по флуд-контролю и вернуть время до момента,
     * когда оно вновь станет возможным.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param floodKey         ключ действия.
     * @param addition         количество добавляемых действий.
     * @param limit            максимальное количество действий, которое может быть совершено за данное время.
     * @param cooldown         длительность, за которую можно совершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return 0, если всё хорошо; иначе время в миллисекундах до момента, когда действие вновь станет выполнимым.
     * @throws IllegalArgumentException если addition < 0, limit < addition или cooldown < 100ms.
     */
    long checkAndAddGetTimeLeft(String floodKey, int addition, int limit, long cooldown, TimeUnit cooldownTimeUnit);

    /**
     * Проверить, доступно ли действие игроку по флуд-контролю и вернуть время до момента,
     * когда игрок вновь сможет выполнить действие.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param playerName       ник игрока.
     * @param floodKey         ключ действия.
     * @param addition         количество добавляемых действий.
     * @param limit            максимальное количество действий, которое игрок может совершить за данное время.
     * @param cooldown         длительность, за которую игрок может завершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return 0, если всё хорошо; иначе время в миллисекундах до момента, когда игрок вновь сможет выполнить действие.
     * @throws IllegalArgumentException если addition < 0, limit < addition или cooldown < 100ms.
     */
    default long checkAndAddGetTimeLeft(String playerName, String floodKey, int addition, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAddGetTimeLeft(playerName.toLowerCase() + "." + floodKey, addition, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * Проверить, доступно ли действие игроку по флуд-контролю и вернуть время до момента,
     * когда игрок вновь сможет выполнить действие.
     * При вызове считается, что игрок выполнил +1 действие.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param playerName       ник игрока.
     * @param floodKey         ключ действия.
     * @param limit            максимальное количество действий, которое игрок может совершить за данное время.
     * @param cooldown         длительность, за которую игрок может завершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return 0, если всё хорошо; иначе время в миллисекундах до момента, когда игрок вновь сможет выполнить действие.
     * @throws IllegalArgumentException если limit < 1 или cooldown < 100ms.
     */
    default long checkAndAddGetTimeLeft(String playerName, String floodKey, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAddGetTimeLeft(playerName, floodKey, 1, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * Проверить, доступно ли действие игроку по флуд-контролю и вернуть время до момента,
     * когда игрок вновь сможет выполнить действие.
     * При вызове считается, что игрок выполнил +1 действие, при этом лимит - тоже всего 1
     * действие за отведенное время.
     * Не нужно использовать один и тот же флуд-ключ с разными ограничениями по времени.
     *
     * @param playerName       ник игрока.
     * @param floodKey         ключ действия.
     * @param cooldown         длительность, за которую игрок может завершить указанное кол-во действий.
     * @param cooldownTimeUnit тайм-юнит длительности.
     * @return 0, если всё хорошо; иначе время в миллисекундах до момента, когда игрок вновь сможет выполнить действие.
     * @throws IllegalArgumentException если cooldown < 100ms.
     */
    default long checkAndAddGetTimeLeft(String playerName, String floodKey, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAddGetTimeLeft(playerName, floodKey, 1, 1, cooldown, cooldownTimeUnit);
    }

    /**
     * @see FloodControl#checkAndAddGetTimeLeft(String, String, int, int, long, TimeUnit)
     */
    default long checkAndAddGetTimeLeft(User user, String floodKey, int addition, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAddGetTimeLeft(user.getName(), floodKey, addition, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * @see FloodControl#checkAndAddGetTimeLeft(String, String, int, long, TimeUnit)
     */
    default long checkAndAddGetTimeLeft(User user, String floodKey, int limit, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAddGetTimeLeft(user.getName(), floodKey, limit, cooldown, cooldownTimeUnit);
    }

    /**
     * @see FloodControl#checkAndAddGetTimeLeft(String, String, long, TimeUnit)
     */
    default long checkAndAddGetTimeLeft(User user, String floodKey, long cooldown, TimeUnit cooldownTimeUnit) {
        return checkAndAddGetTimeLeft(user.getName(), floodKey, cooldown, cooldownTimeUnit);
    }

    /**
     * Отчистить флуд-историю по указанному ключу.
     *
     * @param floodKey ключ действия.
     */
    void clear(String floodKey);

    /**
     * Отчистить флуд-историю игрока по данному ключу.
     *
     * @param playerName ник игрока.
     * @param floodKey   ключ действия.
     */
    default void clear(String playerName, String floodKey) {
        clear(playerName.toLowerCase() + "." + floodKey);
    }

}
