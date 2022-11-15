package net.villenium.game.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface Subcommand {

    String value();

}
