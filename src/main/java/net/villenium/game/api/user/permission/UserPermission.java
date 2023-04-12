package net.villenium.game.api.user.permission;

import net.villenium.game.api.user.User;

public interface UserPermission {

    /**
     * Получить объект пользователя.
     * @return данный пользователь.
     */
    User getUser();

    /**
     * Получить главную группу игрока (группа с самым высоким рангом)
     *
     * @return главная группа.
     */
    PermissionGroup getMainGroup();

    /**
     * Проверить является ли игрок членом персонала.
     *
     * @return результат.
     */
    boolean isStaff();

    /**
     * Получить высшую группу игрока которая не является группой персонала.
     *
     * @return высшая группа которая не является группой персонала.
     */
    PermissionGroup getMaximalNonStaffGroup();

    /**
     * Проверить есть ли у пользователя данная группа.
     *
     * @param group имя группы.
     * @return наличие группы.
     */
    boolean hasGroup(PermissionGroup group);

    /**
     * Проверка на то, есть ли у пользователя права указанной группы.
     * Заметьте: у пользователя может не быть указанной группы, но могут быть ее права.
     *
     * @param group группа.
     * @return true/false.
     */
    default boolean hasGroupRights(PermissionGroup group) {
        switch (group) {
            case USER:
                return true;
            case VIP:
                return isVip();
            case VIP_PLUS:
                return isVipPlus();
            case PREMIUM:
                return isPremium();
            case PREMIUM_PLUS:
                return isPremiumPlus();
            case RICH:
                return isRich();
            case RICH_PLUS:
                return isRichPlus();
            case SPONSOR:
                return isSponsor();
            case SPONSOR_PLUS:
                return isSponsorPlus();
            case UNIQUE:
                return isUnique();
            case MEDIA:
                return isMedia();
            case TESTER:
                return isTester();
            case HELPER:
                return isJrModerator();
            case BUILDER:
                return isBuilder();
            case MODERATOR:
                return isModerator();
            case SENIOR_BUILDER:
                return isSrBuilder();
            case SENIOR_MODERATOR:
                return isSrModerator();
            case DEVELOPER:
                return isDeveloper();
            case GAME_DESIGN:
                return isGameDesigner();
            case ADMINISTRATOR:
                return isAdministrator();
            case HEAD_ADMIN:
                return isHeadAdministrator();
            default:
                throw new IllegalArgumentException("PermissionGroup " + group.name() + " is not supported there");
        }
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isVip() {
        return isVipPlus() || isJrModerator() || isBuilder() || hasGroup(PermissionGroup.VIP);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isVipPlus() {
        return isPremium() || hasGroup(PermissionGroup.VIP_PLUS);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isPremium() {
        return isPremiumPlus() || isModerator() || hasGroup(PermissionGroup.PREMIUM);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isPremiumPlus() {
        return isRich() || hasGroup(PermissionGroup.PREMIUM_PLUS);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isRich() {
        return isRichPlus() || isSrBuilder() || isSrModerator() || hasGroup(PermissionGroup.RICH);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isRichPlus() {
        return isSponsor() || hasGroup(PermissionGroup.RICH_PLUS);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isSponsor() {
        return isSponsorPlus() || hasGroup(PermissionGroup.SPONSOR);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isSponsorPlus() {
        return isUnique() || isDeveloper() || hasGroup(PermissionGroup.SPONSOR_PLUS);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isUnique() {
        return isAdministrator() || hasGroup(PermissionGroup.UNIQUE);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isMedia() {
        return isAdministrator() || hasGroup(PermissionGroup.MEDIA);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isTester() {
        return isSrBuilder() || hasGroup(PermissionGroup.TESTER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isBuilder() {
        return isSrBuilder() || hasGroup(PermissionGroup.BUILDER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isSrBuilder() {
        return isAdministrator() || isCuratorBuilder() || hasGroup(PermissionGroup.SENIOR_BUILDER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isJrModerator() {
        return isModerator() || hasGroup(PermissionGroup.HELPER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isModerator() {
        return isSrModerator() || hasGroup(PermissionGroup.MODERATOR);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isSrModerator() {
        return isAdministrator() || hasGroup(PermissionGroup.SENIOR_MODERATOR);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isGameDesigner() {
        return isAdministrator() || hasGroup(PermissionGroup.GAME_DESIGN);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isDeveloper() {
        return isAdministrator() || hasGroup(PermissionGroup.DEVELOPER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isAdministrator() {
        return isHeadAdministrator() || hasGroup(PermissionGroup.ADMINISTRATOR);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isCuratorTester() {
        return hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.TESTER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isCuratorBuilder() {
        return hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.BUILDER);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isCuratorModerator() {
        return hasGroup(PermissionGroup.ADMINISTRATOR) && hasGroup(PermissionGroup.MODERATOR);
    }

    /**
     * @return наличие группы.
     * @see UserPermission#hasGroup(PermissionGroup)
     */
    default boolean isHeadAdministrator() {
        return hasGroup(PermissionGroup.HEAD_ADMIN);
    }

}
