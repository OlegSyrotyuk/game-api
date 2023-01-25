package net.stickmix.game.api;

import net.stickmix.game.api.annotation.NotImplement;
import net.stickmix.game.api.athena.StorageManager;
import net.stickmix.game.api.bar.Bar;
import net.stickmix.game.api.command.CommandManager;
import net.stickmix.game.api.donate.DonateManager;
import net.stickmix.game.api.economy.Economy;
import net.stickmix.game.api.entity.logic.EntityLogicBuilder;
import net.stickmix.game.api.entity.logic.EntityLogicHelper;
import net.stickmix.game.api.logger.TelegramLogger;
import net.stickmix.game.api.menu.MenuUtil;
import net.stickmix.game.api.minigame.MinigameSettings;
import net.stickmix.game.api.party.PartyManager;
import net.stickmix.game.api.phantom.entity.PhantomEntityFactory;
import net.stickmix.game.api.realm.RealmManager;
import net.stickmix.game.api.tab.Tab;
import net.stickmix.game.api.user.UserManager;

public class GameApi {

    private static PhantomEntityFactory PHANTOM_ENTITY_FACTORY;
    private static Notification NOTIFICATION_MANAGER;
    private static StorageManager STORAGE_MANAGER;
    private static ScoreBoardUtil SCOREBOARD_UTIL;
    private static EntityLogicBuilder ENTITY_LOGIC_BUILDER;
    private static EntityLogicHelper ENTITY_LOGIC_HELPER;
    private static Title TITLE_MANAGER;
    private static Tab TAB_MANAGER;
    private static Bar BAR_MANAGER;
    private static UserManager USER_MANAGER;
    private static CommandManager COMMAND_MANAGER;
    private static MinigameSettings MINIGAME_SETTINGS;
    private static Economy ECONOMY_UTIL;
    private static MenuUtil MENU_UTIL;
    private static RealmManager REALM_MANAGER;
    private static FloodControl FLOOD_CONTROL;
    private static PartyManager PARTY_MANAGER;
    private static DonateManager DONATE_MANAGER;
    private static TelegramLogger TELEGRAM_LOGGER;

    public static PhantomEntityFactory getPhantomEntityFactory() {
        return PHANTOM_ENTITY_FACTORY;
    }

    public static Notification getNotificationManager() {
        return NOTIFICATION_MANAGER;
    }

    public static ScoreBoardUtil getScoreboardUtil() {
        return SCOREBOARD_UTIL;
    }

    public static EntityLogicBuilder getEntityLogicBuilder() {
        return ENTITY_LOGIC_BUILDER;
    }

    public static EntityLogicHelper getEntityLogicHelper() {
        return ENTITY_LOGIC_HELPER;
    }

    public static Title getTitleManager() {
        return TITLE_MANAGER;
    }

    public static Tab getTabManager() {
        return TAB_MANAGER;
    }

    public static Bar getBarManager() {
        return BAR_MANAGER;
    }

    public static UserManager getUserManager() {
        return USER_MANAGER;
    }

    public static CommandManager getCommandManager() {
        return COMMAND_MANAGER;
    }

    @NotImplement
    public static MinigameSettings getMinigameSettings() {
        return MINIGAME_SETTINGS;
    }

    @NotImplement
    public static Economy getEconomyUtil() {
        return ECONOMY_UTIL;
    }

    public static MenuUtil getMenuUtil() {
        return MENU_UTIL;
    }

    public static StorageManager getStorageManager() {
        return STORAGE_MANAGER;
    }

    public static RealmManager getRealmManager() {
        return REALM_MANAGER;
    }

    public static FloodControl getFloodControl() {
        return FLOOD_CONTROL;
    }

    public static PartyManager getPartyManager() {
        return PARTY_MANAGER;
    }

    public static DonateManager getDonateManager() {
        return DONATE_MANAGER;
    }

    public static TelegramLogger getTelegramLogger() {
        return TELEGRAM_LOGGER;
    }
}
