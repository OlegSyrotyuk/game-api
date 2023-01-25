package net.stickmix.game.api.entity.logic;

import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;

import java.util.function.Function;

public interface EntityLogicBuilder {

    /**
     * Добавить случайное брождение.
     *
     * @return этот билдер.
     */
    EntityLogicBuilder randomStroll();

    /**
     * Добавить возвращение к позиции спавна, пока находится в режиме покоя.
     *
     * @param location позиция спавна.
     * @return этот билдер.
     */
    EntityLogicBuilder spawnLocation(Location location);

    /**
     * Добавить повороты в случайные стороны, когда находится в режиме покоя.
     *
     * @return этот билдер.
     */
    EntityLogicBuilder randomLookaround();

    /**
     * Добавить поворот в сторону игроку, когда находится в режиме покоя.
     *
     * @return этот билдер.
     */
    EntityLogicBuilder playerLookaround();

    /**
     * Это необходимо добавить всем сущностям, которые должны атаковать в ближнем бою.
     *
     * @param delayBetweenAttacks задержка между атаками в тиках.
     * @param aggressiveSpeed     скорость во время агрессии от ее базового значения (1.0 - нормальная).
     * @param knockbackLevel      уровень откидывания при ударах (0 для отключения).
     * @return этот билдер.
     */
    EntityLogicBuilder meleeAttack(int delayBetweenAttacks, double aggressiveSpeed, int knockbackLevel);

    /**
     * Это необходимо добавить всем сущностям, которые должны атаковать в дальнем бою.
     * Скорее всего, не будет работать для всех, кроме скелетонов-лучников.
     *
     * @param minAttackSpeed    минимальная задержка между залпами в тиках.
     * @param maxAttackSpeed    максимальная задержка между залпами в тиках.
     * @param maxAttackDistance максимальная дистанция между сущностью и целью для стрельбы.
     * @return этот билдер.
     */
    EntityLogicBuilder rangedAttack(int minAttackSpeed, int maxAttackSpeed, int maxAttackDistance);

    /**
     * Добавить алгоритм поиска цели для атаки.
     * Каждый новый вызов этого метода будет добавлять новый алгоритм поиска с приоритетом большим,
     * чем у всех предыдущих. То есть все переданные алгоритмы будут вызываться с конца, пока не будет найден
     * тот, что не вернет null. После этого исполнения алгоритмов прекратятся.
     *
     * @param selector     функция выбора цели.
     *                     В случае, если возвращает null, цель сбрасывается.
     *                     В случае, если возвращает невалидную сущность (мертвую/из другого мира/ту, что дальше 64 блоков),
     *                     цель также сбрасывается.
     * @param tickingDelay раз во сколько тиков должен вызываться данный алгоритм. На самом деле, он будет
     *                     вызываться каждый тик, но если переданное значение больше 1, то он будет кешировать
     *                     предыдущий результат (разумеется, все проверки на валидность сущности после использования
     *                     алгоритма останутся).
     * @return этот билдер.
     */
    EntityLogicBuilder victimSelector(Function<Creature, LivingEntity> selector, int tickingDelay);

    /**
     * Добавить алгоритм поиска позиции для перемещения. Он будет вызываться каждый тик.
     *
     * @param selector     функция выбора позиции для перемещения.
     *                     В случае, если возвращает null, считается, что позиции для перемещения нет.
     *                     В случае, если возвращает позицию из другого мира, также считается,
     *                     что позиции для перемещения нет.
     * @param tickingDelay раз во сколько тиков должен вызываться данный алгоритм. На самом деле, он будет
     *                     вызываться каждый тик, но если переданное значение больше 1, то он будет кешировать
     *                     предыдущий результат (разумеется, все проверки на валидность локации после использования
     *                     алгоритма останутся).
     * @return этот билдер.
     */
    EntityLogicBuilder targetSelector(Function<Creature, Location> selector, int tickingDelay);

    /**
     * Убрать всю базовую логику сущности и применить ту, что была настроена с помощью данного билдера.
     *
     * @param creature сущность.
     */
    void apply(Creature creature);

}
