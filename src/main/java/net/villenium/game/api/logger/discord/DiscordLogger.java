package net.villenium.game.api.logger.discord;

public interface DiscordLogger {

    /**
     * Получить имя канала в который будет отправлено сообщение.
     * @return имя канала.
     */
    String getChannel();

    /**
     * Отправить сообщение в канал.
     * @param message сообщение.
     * @param args аргументы для сообщения.
     */
    void log(String message, Object... args);
}
