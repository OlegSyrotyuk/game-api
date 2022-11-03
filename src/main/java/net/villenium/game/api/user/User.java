package net.villenium.game.api.user;

import net.villenium.game.api.user.permission.PermissionGroup;
import net.villenium.game.api.user.permission.UserPermission;
import org.bukkit.entity.Player;

import java.util.List;

public interface User {

    /**
     * Получить имя игрока.
     * @return имя игрока.
     */
    String getName();

    /**
     * Получить айпи игрока.
     * @return айпи игрока.
     */
    String getIp();

    /**
     * Получить сервер игрока.
     * @return сервер игрока.
     */
    String getServer();

    /**
     * Получить группы игрока.
     * @return группы игрока.
     */
    List<PermissionGroup> getGroups();

    /**
     * Получить класс для работы с правами игрока.
     * @return права игрока.
     */
    UserPermission getPermission();

    /**
     * Получить время первого входа игрока на проект.
     * @return время.
     */
    long getFirstJoin();

    /**
     * Получить время последнего входа игрока на проект.
     * @return время.
     */
    long getLastJoin();

    /**
     * Получить время которое игрок провел на проекте.
     * @return время.
     */
    long getTotalOnline();

    /**
     * Получить цветное имя игрока которое содержит префикс группы ник и суффикс игрока.
     * Пример: &4&lВладелец olegsyrotyuk &7[Villenium Team]
     * @return полное визуальное имя.
     */
    String getFullDisplayName();

    /**
     * Получить цветное имя игрока которое содержит только префикс группы и ник игрока.
     * @return визуальное имя.
     */
    String getDisplayName();

    /**
     * Получить суффикс игрока.
     * @return суффикс игрока или null.
     */
    String getSuffix();

    /**
     * Получить spigot-объект игрока.
     * @return spigot-объект.
     */
    Player getSpigotPlayer();

}
