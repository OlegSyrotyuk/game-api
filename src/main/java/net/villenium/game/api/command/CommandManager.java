package net.villenium.game.api.command;

import java.util.Arrays;

public interface CommandManager {

    /**
     * Зарегистрировать команду.
     *
     * @param command класс который, реализует команду.
     */
    <T> void registerCommand(Class<T> command);

    /**
     * Зарегистрировать все команды из списка.
     *
     * @param commands список команд.
     */
    default <T> void registerCommands(Class<T>... commands) {
        Arrays.stream(commands).forEach(this::registerCommand);
    }

    /**
     * Зарегистрировать парсер аргументов.
     *
     * @param parser реализация персера.
     */
    <T> void registerArgumentParser(Class<T> type, ArgumentParser<T> parser);
}
