package net.stickmix.game.api.user.permission;

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
    default boolean isVip() {
        return isAdministrator() || hasGroup(PermissionGroup.VIP);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isVipPlus() {
        return isAdministrator() || hasGroup(PermissionGroup.VIP_PLUS);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isPremium() {
        return isAdministrator() || hasGroup(PermissionGroup.PREMIUM);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isPremiumPlus() {
        return isAdministrator() || hasGroup(PermissionGroup.PREMIUM_PLUS);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isRich() {
        return isAdministrator() || hasGroup(PermissionGroup.RICH);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isRichPlus() {
        return isAdministrator() || hasGroup(PermissionGroup.RICH_PLUS);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isSponsor() {
        return isAdministrator() || hasGroup(PermissionGroup.SPONSOR);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isSponsorPlus() {
        return isAdministrator() || hasGroup(PermissionGroup.SPONSOR_PLUS);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isMedia() {
        return isAdministrator() || hasGroup(PermissionGroup.MEDIA);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isUnique() {
        return isAdministrator() || hasGroup(PermissionGroup.UNIQUE);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isBuilder() {
        return isSrBuilder() || hasGroup(PermissionGroup.BUILDER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isSrBuilder() {
        return isCuratorBuilder() || hasGroup(PermissionGroup.SENIOR_BUILDER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isJrModerator() {
        return isModerator() || hasGroup(PermissionGroup.HELPER);
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
    default boolean isSrModerator() {
        return hasGroup(PermissionGroup.SENIOR_MODERATOR);
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
        return hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.BUILDER);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isCuratorModerator() {
        return hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.MODERATOR);
    }

    /**
     * @see UserPermission#hasGroup(PermissionGroup)
     * @return наличие группы.
     */
    default boolean isOwner() {
        return hasGroup(PermissionGroup.HEAD_ADMIN);
    }

}
