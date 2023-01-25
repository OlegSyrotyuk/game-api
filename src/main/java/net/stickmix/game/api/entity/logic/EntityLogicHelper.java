package net.stickmix.game.api.entity.logic;

import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;

import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Function;

public interface EntityLogicHelper {

    /**
     * Получить алгоритм поиска ближайшего игрока для атаки.
     *
     * @param maxDistance радиус поиска.
     * @return указанный алгоритм.
     */
    Function<Creature, LivingEntity> getClosestPlayerSelector(int maxDistance);

    /**
     * Получить алгоритм выбора сущности для атаки, основываясь на информации об уроне, который
     * сущности нанесли данной сущности. Из этого списка будет выбрана сущность, которая нанесла урона
     * более других и которая валидна (находится в том же мире и в пределах указанного радиуса).
     * Если все сущности невалидны, будет возвращен пустой (null) результат, то есть выборка ближайших
     * противников происходить не будет.
     *
     * @param maxDistance радиус поиска.
     * @param damages     функция, получающая по сущности список тех, кто наносил ей урон, вместе с количеством
     *                    этого самого урона.
     * @return указанный алгоритм.
     */
    Function<Creature, LivingEntity> getMostDamageDealerSelector(int maxDistance, Function<Creature, Map<LivingEntity, Integer>> damages);

    /**
     * Получить алгоритм поиска ближайшего противника для атаки.
     *
     * @param maxDistance радиус поиска.
     * @param predicate   должен возвращать true для тех, кого данная сущность может атаковать.
     *                    Например, основываясь на этом методе можно ввести систему фракций, и заставить
     *                    одних монстров атаковать других, но не своих.
     * @return указанный алгоритм.
     */
    Function<Creature, LivingEntity> getClosestEnemySelector(int maxDistance, BiPredicate<Creature, LivingEntity> predicate);

}