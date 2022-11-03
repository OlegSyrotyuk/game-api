package net.villenium.game.api.user.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionGroup {

    USER(1, "&7Игрок", "&7", 'f'),
    YOUTUBE(6, "&f&lYou&c&lTube", "&f&lY&c&lT ", '6'),
    BUILDER(7, "&e&lСтроитель", "&e&lBUILD ", '5'),
    HELPER(8, "&a&lМл. Модератор", "&a&lJr. MOD ", '4'),
    MODERATOR(9, "&a&lМодератор", "&a&lMOD", '3'),
    DEVELOPER(10, "&9&lРазработчик", "&9&lDEV ", '2'),
    ADMINISTRATOR(50, "&4&lАдминистратор", "&c&lHEAD ADM ", '1'),
    HEAD_ADMIN(100, "&4&lГл. Администратор", "&4&lHEAD ADM ", '0');
    //CONSOLE(1000, "&4&lБожество", "&4&l", '0');

    private final int rank;
    private final String name;
    private final String prefix;
    private final char priority;

    public boolean isStaff() {
        return getRank() >= BUILDER.getRank();
    }


}
