package net.stickmix.game.api.athena;

public interface StorageManager {

    /**
     * Получить хранилище.
     *
     * @param name имя хранилища.
     * @param type тип объектов хранилища.
     * @param <T>  тип.
     * @return хранилище
     */
    <T> AthenaStorage<T> create(String name, Class<T> type);

}
