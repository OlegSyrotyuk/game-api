package net.villenium.game.api.athena;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.villenium.game.api.athena.util.AthenaExclusionStrategy;
import net.villenium.game.api.athena.util.AthenaStorageStrategy;
import net.villenium.game.api.athena.util.ItemSerializer;
import org.bukkit.inventory.ItemStack;

public interface StorageManager {

    /**
     * Получить хранилище.
     *
     * @param name имя хранилища.
     * @param type тип объектов хранилища.
     * @param <T>  тип.
     * @return хранилище
     */
    default <T> AthenaStorage<T> create(String name, Class<T> type) {
        return create(name, new GsonBuilder()
                .setFieldNamingStrategy(new AthenaStorageStrategy())
                .setExclusionStrategies(new AthenaExclusionStrategy())
                .registerTypeHierarchyAdapter(ItemStack.class, new ItemSerializer())
                .create(), type);
    }

    /**
     * Получить хранилище.
     *
     * @param name имя хранилища.
     * @param gson сериализатор.
     * @param type тип объектов хранилища.
     * @param <T>  тип.
     * @return хранилище
     */
    <T> AthenaStorage<T> create(String name, Gson gson, Class<T> type);

}
