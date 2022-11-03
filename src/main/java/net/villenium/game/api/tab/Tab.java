package net.villenium.game.api.tab;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public interface Tab {

    /**
     * Отключить обычное распределение игроков по командам (по их правовой группе и званиям).
     */
    void disableDefaultTabNaming();

    /**
     * Создать пустую команду (без игроков).
     * Этот метод не создает команду для каких-либо игроков, лишь инициализирует реализацию TabTeam.
     * Если команда с указанным названием уже существует, предыдущая реализация останется жить (и останется
     * быть отправленной игрокам, у которых она создана и не удалена), но на стороне сервера будет перезаписана.
     * @param name глобальное название команды. Используется, чтобы эту команду можно было получить после
     *             ее создания.
     * @param priority приоритет отображения команды в таб-листе: чем он выше, тем ближе к началу
     *                 списка будут находиться игроки этой команды. Приоритеты всех команд,
     *                 создаваемых для одного игрока, должны быть разными!
     * @param prefix префикс команды. Все & в нем будут заменены на цветовые коды. В отличие от обычного
     *               майнкрафта, длина префикса у нас может быть до 32 символов (но лучше покороче).
     * @param suffix суффикс команды. Все & в нем будут заменены на цветовые коды.
     * @return реализацию TabTeam.
     * @throws IllegalArgumentException если имя/префикс/суффикс - null, если priority отрицательный.
     */
    default TabTeam createEmptyTeam(String name, char priority, String prefix, String suffix) throws IllegalArgumentException {
        return createTeam(name, priority, prefix, suffix, Collections.emptyList());
    }

    /**
     * Создать команду, в которую войдут указанные игроки.
     * Этот метод не создает команду для каких-либо игроков, лишь инициализирует реализацию TabTeam.
     * Если команда с указанным названием уже существует, предыдущая реализация останется жить (и останется
     * быть отправленной игрокам, у которых она создана и не удалена), но на стороне сервера будет перезаписана.
     * @param name глобальное название команды. Используется, чтобы эту команду можно было получить после
     *             ее создания.
     * @param priority приоритет отображения команды в таб-листе: чем он выше, тем ближе к началу
     *                 списка будут находиться игроки этой команды. Приоритеты всех команд,
     *                 создаваемых для одного игрока, должны быть разными!
     * @param prefix префикс команды. Все & в нем будут заменены на цветовые коды. В отличие от обычного
     *               майнкрафта, длина префикса у нас может быть до 32 символов (но лучше покороче).
     * @param suffix суффикс команды. Все & в нем будут заменены на цветовые коды.
     * @param players игроки, которые должны войти в команду.
     * @return реализацию TabTeam.
     * @throws IllegalArgumentException если имя/префикс/суффикс/игроки - null, если priority отрицательный.
     */
    default TabTeam createTeamFromSpigotPlayers(String name, char priority, String prefix, String suffix, Collection<Player> players) throws IllegalArgumentException {
        return createTeam(name, priority, prefix, suffix, players.stream().map(Player::getName).collect(Collectors.toList()));
    }

    /**
     * Создать команду, в которую войдут игроки с указанными никами.
     * Этот метод не создает команду для каких-либо игроков, лишь инициализирует реализацию TabTeam.
     * Если команда с указанным названием уже существует, предыдущая реализация останется жить (и останется
     * быть отправленной игрокам, у которых она создана и не удалена), но на стороне сервера будет перезаписана.
     * @param name глобальное название команды. Используется, чтобы эту команду можно было получить после
     *             ее создания.
     * @param priority приоритет отображения команды в таб-листе: чем он выше, тем ближе к началу
     *                 списка будут находиться игроки этой команды. Приоритеты всех команд,
     *                 создаваемых для одного игрока, должны быть разными!
     * @param prefix префикс команды. Все & в нем будут заменены на цветовые коды. В отличие от обычного
     *               майнкрафта, длина префикса у нас может быть до 32 символов (но лучше покороче).
     * @param suffix суффикс команды. Все & в нем будут заменены на цветовые коды.
     * @param playerNames ники игроков, которые должны войти в команду.
     * @return реализацию TabTeam.
     * @throws IllegalArgumentException если имя/префикс/суффикс/ники игроков - null, если priority отрицательный.
     */
    TabTeam createTeam(String name, char priority, String prefix, String suffix, Collection<String> playerNames) throws IllegalArgumentException;

    /**
     * Получить команду по ее названию.
     * @param name название команды.
     * @return реализацию TabTeam с указанным названием или null, если команды по заданному названию нет.
     * @throws IllegalArgumentException если name - null.
     */
    TabTeam getTeamByName(String name) throws IllegalArgumentException;

    /**
     * Получить все существующие команды из памяти сервера.
     * @return все существующие команды.
     */
    Map<String, TabTeam> getTeams();

    /**
     * Удалить команду по ее имени.
     * @param name имя команды.
     * @return true, если такая существовала и удаление прошло успешно; false в противном случае.
     */
    boolean removeTeam(String name);

    /**
     * Удаляет все существующие команды из памяти сервера (но не удаляет их у игроков).
     */
    void wipeAllTeams();

}
