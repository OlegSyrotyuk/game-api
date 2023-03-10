package net.villenium.game.api.event;

/**
 * Класс-обертка, имплементирующий IGameListener и автоматически регистрирующий себя в GameEventManager.
 */
public class GameListener implements IGameListener {

    protected GameListener() {
        GameEventManager.register(this);
    }

}
