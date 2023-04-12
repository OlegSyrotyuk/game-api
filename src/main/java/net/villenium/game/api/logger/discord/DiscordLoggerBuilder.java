package net.villenium.game.api.logger.discord;

public interface DiscordLoggerBuilder {

    /**
     * Установить имя канала с логом.
     * @param name имя канала.
     * @return этот билдер.
     */
    DiscordLoggerBuilder channel(String name);

    /**
     * Добавить роль, которая может видеть канал с логами. (Можно несколько)
     * @param role роль.
     * @return этот билдер.
     */
    DiscordLoggerBuilder addRole(DiscordRole role);

    /**
     * Получить объект логгера.
     * @return объект логгера.
     */
    DiscordLogger build();
}
