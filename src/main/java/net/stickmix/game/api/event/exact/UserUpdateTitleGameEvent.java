package net.stickmix.game.api.event.exact;

import lombok.Data;
import net.stickmix.game.api.event.GameEvent;
import net.stickmix.game.api.user.User;

/**
 * Событие вызывается, когда пользователь обновляет свой титул.
 */
@Data
public class UserUpdateTitleGameEvent extends GameEvent {

    private final User user;
    private final String oldTitle;
    private final String newTitle;

}
