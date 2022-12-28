package net.stickmix.game.api.user;

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
     * Есть ли у пользователя бета статус.
     * @return результат.
     */
    boolean hasBeta();

    /**
     * Установить бета статус.
     * @param value true/false
     */
    void setBeta(boolean value);

    /**
     * Отображен ли бета статус.
     * @return результат.
     */
    boolean isBetaShown();

    /**
     * Установить отображение бета статуса.
     * @param value true/false
     */
    void setBetaShown(boolean value);

    /**
     * Сохранить изменения персонализации.
     */
    void save();

}
