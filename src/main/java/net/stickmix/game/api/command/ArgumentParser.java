package net.stickmix.game.api.command;

import org.bukkit.command.CommandSender;

public interface ArgumentParser<T> {

    T parse(String object, CommandSender sender);

}
