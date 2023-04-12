package net.villenium.game.api.logger;

import net.villenium.game.api.logger.discord.DiscordLoggerBuilder;

public interface LoggerManager {

    DiscordLoggerBuilder discord();

}
