package net.villenium.game.api.user;

import net.villenium.game.api.annotation.NotImplement;
import net.villenium.game.api.party.Party;
import net.villenium.game.api.realm.Realm;
import net.villenium.game.api.user.permission.PermissionGroup;
import net.villenium.game.api.user.permission.UserPermission;
import org.bukkit.entity.Player;

import java.util.List;

public interface User {

    /**
     * Получить имя пользователя.
     *
     * @return имя пользователя.
     */
    String getName();

    /**
     * Получить айпи адрес пользователя.
     *
     * @return айпи пользователя.
     */
    String getIp();

    /**
     * Получить объект реалма на котором находится пользователь, если такой реалм существует на координаторе проекта.
     * В ином случае метод вернет null.
     *
     * @return реалм пользователя.
     */
    Realm getRealm();

    /**
     * Получить все группы пользователя.
     *
     * @return группы пользователя.
     */
    List<PermissionGroup> getGroups();

    /**
     * Получить класс для работы с правами пользователя.
     *
     * @return права пользователя.
     */
    UserPermission getPermission();

    /**
     * Получить класс для работы с персонализацией.
     *
     * @return персонализация пользователя.
     */
    UserPersonalization getPersonalization();

    /**
     * Получить класс для работы с настройками пользователя.
     *
     * @return настройки пользователя.
     */
    UserSettings getSettings();

    /**
     * Получить класс для работы с уровнем пользователя.
     *
     * @return уровень пользователя.
     */
    @NotImplement
    UserNetworkLevel getNetworkLevel();

    /**
     * Получить время первого входа пользователя на проект.
     *
     * @return время.
     */
    long getFirstJoin();

    /**
     * Получить время последнего входа пользователя на проект.
     *
     * @return время.
     */
    long getLastJoin();

    /**
     * Получить время которое пользователь провел на проекте.
     * Обновляется при каждом выходе пользователя с проекта.
     *
     * @return время.
     */
    long getTotalOnline();

    /**
     * Получить цветное имя игрока которое содержит префикс группы ник и суффикс пользователя.
     * Пример: &4&lВладелец olegsyrotyuk &7[Villenium Team]
     *
     * @return полное визуальное имя.
     */
    String getFullDisplayName();

    /**
     * Получить цветное имя игрока которое содержит только префикс группы и ник пользователя.
     *
     * @return визуальное имя.
     */
    String getDisplayName();

    /**
     * Получить префикс пользователя.
     * Будет учтен бета статус.
     *
     * @return префикс пользователя.
     */
    String getPrefix();

    /**
     * Получить spigot-объект игрока.
     *
     * @return spigot-объект.
     */
    Player getSpigotPlayer();

    /**
     * Проверить является ли пользователь онлайн на проекте.
     *
     * @return true если пользователь находится на любом сервере проекта.
     */
    boolean isOnline();

    /**
     * Получить прокси сервер на котором находится пользователь.
     *
     * @return айди прокси сервера.
     */
    String getProxy();

    /**
     * Получить максимальное доступное количество друзей и участников тусовки пользователя.
     *
     * @return максимальное количество.
     */
    int getPartyAndFriendsLimit();

    /**
     * Получить тусовку в которой состоит пользователь.
     *
     * @return объект тусовки или null.
     */
    Party getParty();

}
