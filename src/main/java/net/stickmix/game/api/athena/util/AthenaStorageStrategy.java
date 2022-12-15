package net.stickmix.game.api.athena.util;

import com.google.gson.FieldNamingStrategy;
import net.stickmix.game.api.athena.annotation.Id;
import net.stickmix.game.api.athena.annotation.Name;

import java.lang.reflect.Field;

public class AthenaStorageStrategy implements FieldNamingStrategy {

    @Override
    public String translateName(Field field) {
        if (field.isAnnotationPresent(Id.class)) {
            return "_id";
        }
        if (field.isAnnotationPresent(Name.class)) {
            return field.getAnnotation(Name.class).name();
        }
        return field.getName();
    }
}
