package net.stickmix.game.api.command.access;

import net.stickmix.game.api.user.permission.UserPermission;

public interface CommandAccess {

    /**
     * Проверить имеет ли доступ пользователь к использованию команды.
     *
     * @param permission права игрока.
     * @return результат доступа.
     */
    CommandAccessResult hasAccess(UserPermission permission);

}
