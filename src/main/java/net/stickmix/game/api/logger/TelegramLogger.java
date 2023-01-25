package net.stickmix.game.api.logger;

public interface TelegramLogger {

    /**
     * Отправить сообщение в чат StickMix | Logs
     *
     * @param category то что будет отображаться с хэштегом для поиска.
     * @param message  сообщение.
     * @param args     аргументы.
     */
    void log(String category, String message, Object... args);

    default void warn(String message, Object... args) {
        log("warning", message, args);
    }

    default void error(String message, Object... args) {
        log("error", message, args);
    }

}
