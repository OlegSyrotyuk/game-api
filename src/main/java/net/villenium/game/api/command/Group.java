package net.villenium.game.api.command;

import net.villenium.game.api.user.permission.PermissionGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Group {

    PermissionGroup group() default PermissionGroup.USER;

}
