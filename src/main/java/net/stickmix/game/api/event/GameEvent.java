package net.stickmix.game.api.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameEvent {

    public void call() {
        GameEventManager.call(this);
    }

}
