package net.villenium.game.api.command;

public interface CommandManager {

    /**
     * Зарегистрировать команду.
     * @param command класс который реализует команду.
     */
    <T> void registerCommand(Class<T> command);
}
