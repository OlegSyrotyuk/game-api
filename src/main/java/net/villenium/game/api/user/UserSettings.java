package net.villenium.game.api.user;

import java.util.List;

public interface UserSettings {

    /**
     * Открыты ли личные сообщения пользователя.
     * @return результат.
     */
    boolean isPrivateMessageEnabled();

    /**
     * Включен ли у пользователя чат группы.
     * @return результат.
     */
    boolean isPartyChatEnabled();

    /**
     * Включен ли у пользователя чат персонала.
     * @return результат.
     */
    boolean isStaffChatEnabled();

    /**
     * Включен ли у пользователя вип чат.
     * @return результат.
     */
    boolean isVipChatEnabled();

    /**
     * Включен ли у пользователя прием приглашений в группу.
     * @return результат.
     */
    boolean isPartyInvitationsEnabled();

    /**
     * Отключить или включить личные сообщения пользователя.
     * @param value значение.
     */
    void enablePrivateMessage(boolean value);

    /**
     * Отключить или включить прием сообщений из чата группы.
     * @param value значение.
     */
    void enablePartyChat(boolean value);

    /**
     * Отключить или включить прием сообщений из чата персонала.
     * @param value значение.
     */
    void enableStaffChat(boolean value);

    /**
     * Отключить или включить прием приглашений в группу.
     * @param value
     */
    void enablePartyInvitations(boolean value);

    /**
     * Получить список пользователей которых игнорирует пользователь.
     * @return список пользователей.
     */
    List<User> getIgnoreList();

    /**
     * Добавить пользователя в список игнора.
     * @param user пользователь.
     */
    void ignoreUser(User user);

    /**
     * Убрать пользователя из списка игнора.
     * @param user пользователь.
     */
    void unignoreUser(User user);

    /**
     * Сохранить изменения персонализации.
     */
    void save();

}
