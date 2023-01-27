package net.stickmix.game.api.event.exact;

import lombok.Data;
import net.stickmix.game.api.event.GameEvent;

@Data
public class PhantomEntityInteractGameEvent extends GameEvent {

    private final Type type;

    public enum Type {
        RIGHT_CLICK,
        LEFT_CLICK
    }

}
