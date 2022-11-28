package net.villenium.game.api.user;

public interface UserPersonalization {

    /**
     * Получить суффикс пользователя.
     * @return суффикс или null.
     */
    String getSuffix();

    /**
     * Установить суффикс пользователя.
     * @param suffix суффикс.
     */
    void setSuffix(String suffix);

    /**
     * Сохранить изменения персонализации.
     */
    void save();

}
