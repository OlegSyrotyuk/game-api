package net.villenium.game.api.user;

public interface UserNetworkLevel {

    /**
     * Получить уровень игрока.
     * @return уровень игрока.
     */
    int getLevel();

    /**
     * Получить количество опыта игрока (не суммарное, а с получения последнего уровня).
     * @return опыт игрока.
     */
    int getExperience();

    /**
     * Получить количество опыта, которое нужно для достижения следующего уровня.
     * @return количество опыта до следующего уровня.
     */
    int getExperienceForNextLevel();

    /**
     * Получить количество опыта, которого не хватает для получения следующего уровня.
     * @return количество опыта, которого не хватает для получения следующего уровня.
     */
    default int getExperienceLeftForNextLevel() {
        return getExperienceForNextLevel() - getExperience();
    }

    /**
     * Добавить опыт игроку.
     * @param amount количество добавляемого опыта (должно быть больше 0).
     * @throws IllegalArgumentException если переданное значение меньше 1.
     */
    void addExperience(int amount) throws IllegalArgumentException;

}
