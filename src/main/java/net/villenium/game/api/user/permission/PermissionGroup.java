package net.villenium.game.api.user.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionGroup {

    USER(1, "&7Игрок", "&7", 'f'),
    YOUTUBE(6, "&f&lYou&c&lTube", "&f&lY&c&lT ", '7'),
    UNIQUE(7, "&d&lУникальный", "&d&lUNIQUE ", '6'),
    BUILDER(8, "&e&lСтроитель", "&e&lBUILD ", '5'),
    HELPER(9, "&a&lМл. Модератор", "&a&lJr. MOD ", '4'),
    MODERATOR(10, "&a&lМодератор", "&a&lMOD", '3'),
    DEVELOPER(11, "&9&lРазработчик", "&9&lDEV ", '2'),
    ADMINISTRATOR(50, "&4&lАдминистратор", "&c&lADM ", '1'),
    HEAD_ADMIN(100, "&4&lГл. Администратор", "&4&lHEAD ADM ", '0');

    private final int rank;
    private final String name;
    private final String prefix;
    private final char priority;

    public boolean isStaff() {
        return getRank() >= BUILDER.getRank();
    }


}
