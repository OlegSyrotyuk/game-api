package net.villenium.game.api.athena.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import net.villenium.game.api.athena.annotation.IgnoreField;

public class AthenaExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotations().stream().anyMatch(annotation -> annotation.annotationType() == IgnoreField.class);
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
