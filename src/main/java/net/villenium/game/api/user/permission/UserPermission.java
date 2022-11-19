package net.villenium.game.api.user.permission;

import net.villenium.game.api.user.User;

public interface UserPermission {

    /**
     * Получить главную группу игрока (группа с самым высоким рангом)
     * @return главная группа.
     */
    PermissionGroup getMainGroup();

    /**
     * Проверить является ли игрок членом персонала.
     * @return результат.
     */
    boolean isStaff();

    /**
     * Получить высшую группу игрока которая не является группой персонала.
     * @return высшая группа которая не является группой персонала.
     */
    PermissionGroup getMaximalNonStaffGroup();

    /**
     * Проверить есть ли у пользователя данная группа.
     * @param group имя группы.
     * @return наличие группы.
     */
    boolean hasGroup(PermissionGroup group);

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isYouTube() {
        return hasGroup(PermissionGroup.YOUTUBE);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isUnique() {
        return hasGroup(PermissionGroup.UNIQUE);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isBuilder() {
        return hasGroup(PermissionGroup.BUILDER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isJrModerator() {
        return isModerator() && hasGroup(PermissionGroup.HELPER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isModerator() {
        return isAdministrator() || hasGroup(PermissionGroup.MODERATOR);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isDeveloper() {
        return isAdministrator() || hasGroup(PermissionGroup.DEVELOPER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isAdministrator() {
        return isOwner() || hasGroup(PermissionGroup.ADMINISTRATOR);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isCuratorBuilder() {
        return isOwner() || hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.BUILDER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isCuratorModerator() {
        return isOwner() || hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.MODERATOR);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isOwner() {
        return hasGroup(PermissionGroup.HEAD_ADMIN);
    }

}
