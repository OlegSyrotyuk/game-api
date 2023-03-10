package net.villenium.game.api.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameCancellableEvent extends GameEvent {

    private boolean cancelled;

    /**
     * Пометить событие отмененным.
     */
    public void setCancelled() {
        this.cancelled = true;
    }

    /**
     * Повлиять на отмененность события.
     *
     * @param value true/false.
     */
    public void setCancelled(boolean value) {
        this.cancelled = value;
    }

    /**
     * Проверка на то, было ли отменено это событие одним из его обработчиков.
     *
     * @return true/false.
     */
    public boolean isCancelled() {
        return this.cancelled;
    }

}
