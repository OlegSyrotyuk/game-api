package net.villenium.game.api.command;

public interface CommandManager {

    /**
     * Зарегистрировать команду.
     *
     * @param command класс который реализует команду.
     */
    <T> void registerCommand(Class<T> command);

    /**
     * Зарегистрировать парсер аргументов.
     *
     * @param parser реализация персера.
     */
    <T> void registerArgumentParser(Class<T> type, ArgumentParser<T> parser);
}
