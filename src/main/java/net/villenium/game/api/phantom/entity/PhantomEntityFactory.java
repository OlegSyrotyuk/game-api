package net.villenium.game.api.phantom.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public interface PhantomEntityFactory {

    /**
     * Получить заспавненную фантомную сущность по ее идентификатору.
     *
     * @param id идентификатор сущности.
     * @return сущность или null.
     */
    PhantomEntity getById(int id);

    /**
     * Создать фантомного игрока.
     * Это действие не спавнит сущность, а перед спавном нужно установить ей локацию!
     *
     * @param uuid     UUID игрока.
     * @param name     ник игрока.
     * @param skinName ник игрока, скин которого должен быть у данного фантомного игрока.
     * @return фантомного игрока.
     */
    PhantomPlayer createPlayer(UUID uuid, String name, String skinName);

    PhantomPlayer createPlayer(int id, UUID uuid, String name, String skinName);

    /**
     * @see PhantomEntityFactory#createPlayer(String, String)
     */
    default PhantomPlayer createPlayer(UUID uuid, String name) {
        return createPlayer(uuid, name, name);
    }

    /**
     * @see PhantomEntityFactory#createPlayer(String, String)
     */
    default PhantomPlayer createPlayer(String name, String skinName) {
        return createPlayer(UUID.randomUUID(), name, skinName);
    }

    default PhantomPlayer createPlayer(int id, String name, String skinName) {
        return createPlayer(id, UUID.randomUUID(), name, skinName);
    }

    /**
     * @see PhantomEntityFactory#createPlayer(String, String)
     */
    default PhantomPlayer createPlayer(String name) {
        return createPlayer(name, name);
    }

    /**
     * Создать фантомную сущность.
     * Это действие не спавнит сущность, а перед спавном нужно установить ей локацию!
     *
     * @param id   идентификатор сущности.
     * @param type тип сущности.
     * @return фантомную сущность.
     * @throws IllegalArgumentException если type == PLAYER.
     */
    PhantomEntity createEntity(int id, EntityType type) throws IllegalArgumentException;

    /**
     * @see PhantomEntityFactory#createEntity(int, EntityType)
     */
    default PhantomEntity createEntity(EntityType type) {
        return createEntity(getRandomIdForPhantomEntity(), type);
    }

    /**
     * Создать фантомную сущность с возможностью экипирования.
     * Это действие не спавнит сущность, а перед спавном нужно установить ей локацию!
     *
     * @param id   идентификатор сущности.
     * @param type тип сущности.
     * @return фантомную сущность.
     * @throws IllegalArgumentException если указанный тип не может быть экипированным или == PLAYER.
     */
    PhantomEquippableEntity createEquippableEntity(int id, EntityType type) throws IllegalArgumentException;

    /**
     * @see PhantomEntityFactory#createEquippableEntity(int, EntityType)
     */
    default PhantomEquippableEntity createEquippableEntity(EntityType type) throws IllegalArgumentException {
        return createEquippableEntity(getRandomIdForPhantomEntity(), type);
    }

    /**
     * Создать фантомную голограмму.
     *
     * @param id   идентификатор сущности.
     * @param text текст (имя) голограммы с заменой & на цветовые коды.
     * @return фантомную голограмму.
     */
    PhantomHologram createHologram(int id, String text);

    /**
     * @see PhantomEntityFactory#createHologram(int, String)
     */
    default PhantomHologram createHologram(String text) {
        return createHologram(getRandomIdForPhantomEntity(), text);
    }

    /**
     * Создать фантомную голограмму с различными вариациями для разных групп игроков.
     *
     * @param id идентификатор сущности.
     * @return фантомную вариативную голограмму.
     */
    PhantomVariativeHologram createVariativeHologram(int id);

    /**
     * @see PhantomEntityFactory#createVariativeHologram(int)
     */
    default PhantomVariativeHologram createVariativeHologram() {
        return createVariativeHologram(getRandomIdForPhantomEntity());
    }

    default int getRandomIdForPhantomEntity() {
        return ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE - 40000) + 30000;
    }

    /**
     * Заспавнить топ игроков данного минирежима по указанной статистике.
     *
     * @param spawnLocations позиции для спавна игроков из топа. Порядок топа определяется порядком итерирования.
     * @param statName       название статистики, по которой нужно строить топ.
     * @param statNamer      функция для получения имени статистики в правильном падеже по количеству
     *                       (например, 1 - убийство, 2 - убийства, 5 - убийств).
     * @return список заспавненных игроков.
     */
    List<PhantomPlayer> spawnTop(List<Location> spawnLocations, String statName, Function<Integer, String> statNamer);

    /**
     * Заспавнить топ игроков данного минирежима по указанной статистике.
     *
     * @param spawnLocations позиции для спавна игроков из топа. Порядок топа определяется порядком итерирования.
     * @param values         мапа с статистикой игроков.
     * @param statNamer      функция для получения имени статистики в правильном падеже по количеству
     *                       (например, 1 - убийство, 2 - убийства, 5 - убийств).
     * @return список заспавненных игроков.
     */
    List<PhantomPlayer> spawnTop(List<Location> spawnLocations, Map<String, Long> values, Function<Long, String> statNamer);

    /**
     * Заспавнить топ из голограмм по указанной мапе.
     *
     * @param title     название топа (появится сверху).
     * @param position  позиция для спавна.
     * @param values    мапа с статистикой игроков.
     * @param statNamer функция для получения имени статистики в правильном падеже по количеству
     *                  (например, 1 - убийство, 2 - убийства, 5 - убийств).
     * @return список заспавненных голограмм.
     */
    List<PhantomVariativeHologram> spawnHologramTop(String title, Location position, Map<String, ? extends Number> values, Function<Number, String> statNamer);
}
