package net.stickmix.game.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface GameEventHandler {

    /**
     * Приоритет исполнения.
     * Считается от Byte.MIN_VALUE до Byte.MAX_VALUE (не нужно использовать минимальное значение, оно системное).
     * @return приоритет исполнения.
     */
    byte priority() default 0;

    /**
     * Показатель того, нужно ли обрабатывать это событие, если один из предыдущих обработчиков пометил его, как отмененное.
     * @return true/false.
     */
    boolean ignoreCancelled() default true;

}
