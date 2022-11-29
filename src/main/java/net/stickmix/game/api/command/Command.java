package net.stickmix.game.api.command;

import net.stickmix.game.api.user.permission.PermissionGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String value();
    PermissionGroup group() default PermissionGroup.USER;
}
