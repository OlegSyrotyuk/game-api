package net.villenium.game.api.event.exact;

import lombok.Data;
import net.villenium.game.api.event.GameEvent;
import net.villenium.game.api.user.User;

/**
 * Событие вызывается, когда пользователь отключается от прокси-сервера, находясь на данном реалме.
 * Т.е событие будет вызвано лишь на текущем реалме, а не всех реалмах.
 */
@Data
public class UserProjectQuitGameEvent extends GameEvent {

    private final User user;

}
