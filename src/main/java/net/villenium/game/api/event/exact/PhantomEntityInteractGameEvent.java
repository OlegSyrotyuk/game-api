package net.villenium.game.api.event.exact;

import lombok.Data;
import net.villenium.game.api.event.GameEvent;
import net.villenium.game.api.phantom.entity.PhantomEntity;
import org.bukkit.entity.Player;

@Data
public class PhantomEntityInteractGameEvent extends GameEvent {

    private final Player whoClicked;
    private final PhantomEntity phantomEntity;
    private final Type type;

    public enum Type {
        RIGHT_CLICK,
        LEFT_CLICK
    }

}
