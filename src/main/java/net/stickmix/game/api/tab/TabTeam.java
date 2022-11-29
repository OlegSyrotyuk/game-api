package net.stickmix.game.api.tab;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;


public interface TabTeam {

    /**
     * Получить название команды.
     * @return название команды.
     */
    String getName();

    /**
     * Получить приоритет команды.
     * @return приоритет команды.
     */
    char getPriority();

    /**
     * Получить префикс команды (с заменой & на цветовой код).
     * @return префикс команды.
     */
    String getPrefix();

    /**
     * Установить новый префикс команды (с заменой & на цветовой код).
     * @param prefix новый префикс команды.
     */
    default void setPrefix(String prefix) {
        setPrefixAndSuffix(prefix, getSuffix());
    }

    /**
     * Получить суффикс команды (с заменой & на цветовой код).
     * @return суффикс команды.
     */
    String getSuffix();

    /**
     * Установить новый суффикс команды (с заменой & на цветовой код).
     * @param suffix новый суффикс команды.
     */
    default void setSuffix(String suffix) {
        setPrefixAndSuffix(getPrefix(), suffix);
    }

    /**
     * Установить новые префикс и суффикс команды (с заменой & на цветовой код).
     * @param prefix новый префикс команды.
     * @param suffix новый суффикс команды.
     */
    void setPrefixAndSuffix(String prefix, String suffix);

    /**
     * Получить игроков в команде.
     * Этот метод в своей логике сложнее, чем getPlayerNames.
     * @return игроки этой команды.
     */
    Collection<Player> getPlayers();

    /**
     * Получить ники игроков в команде.
     * Этот метод в своей логике проще, чем getPlayers.
     * @return ники игроков этой команды.
     */
    Collection<String> getPlayerNames();

    /**
     * Создать команду для всех игроков, которые сейчас есть на сервере.
     * Для каждого вновь заходящего на сервер игрока нужно будет делать create().
     */
    void createForAll();

    /**
     * Создать команду для указанных игроков.
     * @param players игроки.
     */
    default void createForPlayers(Collection<Player> players) {
        create(players.stream().map(Player::getName).collect(Collectors.toList()));
    }

    /**
     * Создать команду для указанных игроков.
     * @param playerNames ники игроков.
     */
    void create(Collection<String> playerNames);

    /**
     * Удалить команду для всех игроков.
     */
    void deleteForAll();

    /**
     * Удалить команду для указанных игроков.
     * @param players игроки.
     */
    default void deleteForPlayers(Collection<Player> players) {
        delete(players.stream().map(Player::getName).collect(Collectors.toList()));
    }

    /**
     * Удалить команду для указанных игроков.
     * @param playerNames ники игроков.
     */
    void delete(Collection<String> playerNames);

    /**
     * Добавить игрока в команду.
     * Если он уже состоит в этой команде, ничего нового не произойдет.
     * Игрок может состоять в нескольких командах (т.к. команды могут существовать для разных игроков),
     * поэтому важно помнить, чтобы для одного и того же игрока не существовало двух разных команд,
     * в которых состоит один и тот же игрок, иначе будет непредсказуемое поведение.
     * Важно: добавление игрока в команду не означает, что она создана для него.
     * @param player игрок.
     */
    default void addPlayer(Player player) {
        addPlayer(player.getName());
    }

    /**
     * Добавить игрока в команду.
     * Если он уже состоит в этой команде, ничего нового не произойдет.
     * Игрок может состоять в нескольких командах (т.к. команды могут существовать для разных игроков),
     * поэтому важно помнить, чтобы для одного и того же игрока не существовало двух разных команд,
     * в которых состоит один и тот же игрок, иначе будет непредсказуемое поведение.
     * Важно: добавление игрока в команду не означает, что она создана для него.
     * @param playerName ник игрока.
     */
    default void addPlayer(String playerName) {
        addPlayers(Collections.singleton(playerName));
    }

    /**
     * Добавить игроков в команду.
     * Если он уже состоит в этой команде, ничего нового не произойдет.
     * Игрок может состоять в нескольких командах (т.к. команды могут существовать для разных игроков),
     * поэтому важно помнить, чтобы для одного и того же игрока не существовало двух разных команд,
     * в которых состоит один и тот же игрок, иначе будет непредсказуемое поведение.
     * Важно: добавление игрока в команду не означает, что она создана для него.
     * @param playerNames ники игроков.
     */
    void addPlayers(Collection<String> playerNames);

    /**
     * Убрать игрока из команды.
     * Если он в ней не состоит, ничего не произойдет.
     * Удаление игрока из команды не означает, что команда перестанет для него существовать,
     * то есть он все еще сможет видеть других членов этой команды.
     * @param player игрок.
     */
    default void removePlayer(Player player) {
        removePlayer(player.getName());
    }

    /**
     * Убрать игрока из команды.
     * Если он в ней не состоит, ничего не произойдет.
     * Удаление игрока из команды не означает, что команда перестанет для него существовать,
     * то есть он все еще сможет видеть других членов этой команды.
     * @param playerName ник игрока.
     */
    default void removePlayer(String playerName) {
        removePlayers(Collections.singleton(playerName));
    }

    /**
     * Убрать игроков из команды.
     * Если он в ней не состоит, ничего не произойдет.
     * Удаление игрока из команды не означает, что команда перестанет для него существовать,
     * то есть он все еще сможет видеть других членов этой команды.
     * @param playerNames ники игроков.
     */
    void removePlayers(Collection<String> playerNames);

    /**
     * Сделать команду авто-видимой.
     * Это значит, что она будет автоматически создаваться для заходящих на сервер игроков
     * и автоматически удаляться для выходящих с сервера игроков.
     * @param value true/false.
     * @throws IllegalStateException если value = true и она уже авто-видимая или если
     * value = false и она и так не является авто-видимой.
     */
    void setAutoVisible(boolean value) throws IllegalStateException;

    /**
     * Установить форсированное имя команды для передачи в пакетах на клиенты игроков.
     * Отвечает за местоположение в топе и другие вещи.
     * @param name название.
     * @throws IllegalStateException если переданный аргумент null, пустой или длиннее 16 символов.
     */
    void setForcedPacketName(String name) throws IllegalStateException;

}
