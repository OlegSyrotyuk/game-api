package net.villenium.game.api.athena.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import net.villenium.game.api.athena.DataOptions;
import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Athena {

    public static final DataOptions DEFAULT_OPTIONS = DataOptions.builder()
            .cache(true)
            .cacheTime(TimeUnit.HOURS.toSeconds(4))
            .build();

    public static final DataOptions WITHOUT_CACHING = DataOptions.builder()
            .cache(false)
            .build();

    public static final Gson ATHENA_DEFAULT_GSON = new GsonBuilder()
            .setFieldNamingStrategy(new AthenaStorageStrategy())
            .create();

    public static final Logger LOGGER = LoggerFactory.getLogger("Athena");
    @Getter
    private static final GsonBuilder gsonBuilder = new GsonBuilder()
            .setFieldNamingStrategy(new AthenaStorageStrategy())
            .setExclusionStrategies(new AthenaExclusionStrategy())
            .registerTypeHierarchyAdapter(ItemStack.class, new ItemSerializer());

}
