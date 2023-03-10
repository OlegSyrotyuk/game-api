package net.villenium.game.api.command.access;

import net.villenium.game.api.user.permission.UserPermission;

public interface CommandAccess {

    /**
     * Проверить имеет ли доступ пользователь к использованию команды.
     *
     * @param permission права игрока.
     * @return результат доступа.
     */
    CommandAccessResult hasAccess(UserPermission permission);

}
