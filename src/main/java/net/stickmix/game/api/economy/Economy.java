package net.stickmix.game.api.economy;

import net.stickmix.game.api.annotation.NotImplement;
import net.stickmix.game.api.GameApi;
import net.stickmix.game.api.user.User;
import org.bukkit.entity.Player;

@NotImplement
public interface Economy {

    /**
     * Получить валюту данного сервера.
     * @return валюта сервера.
     */
    String getCurrency();

    /**
     * Дать пользователю указанную валюту в указанном размере.
     * @param user пользователь.
     * @param currency валюта.
     * @param value значение.
     */
    void change(User user, String currency, double value);

    /**
     * Дать пользователю дефолтную валюту этого сервера в указанном размере.
     * @param user пользователь.
     * @param value значение.
     */
    default void change(User user, double value) {
        change(user, getCurrency(), value);
    }

    /**
     * @see Economy#change(Player, double) (User, double) только  вызов от bukkit игрока.
     * @param player bukkit игрок.
     * @param value значение.
     */
    default void change(Player player, double value) {
        change(GameApi.getUserManager().get(player), value);
    }

}
