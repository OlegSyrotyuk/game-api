package net.villenium.game.api.minigame;

import net.villenium.game.api.annotation.NotImplement;

@NotImplement
public interface MinigameSettings {

    /**
     * Установить тип режима игры.
     *
     * @param type тип режима игры.
     */
    void setMinigameType(MinigameType type);

    /**
     * Установить дефолтный формат чата и таба.
     * Chat: HEAD ADM CraftElvira [VilleniumTM]: всем привет!
     * Tab: HEAD ADM CraftElvira [VilleniumTM]
     */
    void defaultChatAndTab();

    /**
     * Установить формат чата для данного сервера.
     * Пример: &8[&6%player_level%&8] %user_prefix%%user_name% %user_title%&8: &7
     *
     * @param format формат чата с использованием плейсхолдеров.
     */
    void setChatFormat(String format);

    /**
     * Установить формат таба для данного сервера.
     * Пример: &8[&6%player_level%&8] %user_prefix%%user_name% %user_title%
     *
     * @param format формат таба с использованием плейсхолдеров.
     */
    void setTabFormat(String format);

    /**
     * Установить дефолтную валюту сервера.
     * Эта валюта будет использоваться для переводов /pay
     * А также при добавлении/вычитании без указывания названия валюты.
     *
     * @param currency название валюты.
     */
    void setDefaultCurrency(String currency);

}
