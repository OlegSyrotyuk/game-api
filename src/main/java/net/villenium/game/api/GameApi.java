package net.villenium.game.api;

import net.villenium.game.api.annotation.NotImplement;
import net.villenium.game.api.bar.Bar;
import net.villenium.game.api.command.CommandManager;
import net.villenium.game.api.entity.logic.EntityLogicBuilder;
import net.villenium.game.api.entity.logic.EntityLogicHelper;
import net.villenium.game.api.phantom.entity.PhantomEntityFactory;
import net.villenium.game.api.tab.Tab;
import net.villenium.game.api.user.UserManager;
import net.villenium.game.api.util.ChatUtil;

public class GameApi {

    private static PhantomEntityFactory PHANTOM_ENTITY_FACTORY;
    private static Notification NOTIFICATION_MANAGER;
    private static ScoreBoard SCOREBOARD_MANAGER;
    private static EntityLogicBuilder ENTITY_LOGIC_BUILDER;
    private static EntityLogicHelper ENTITY_LOGIC_HELPER;
    private static Title TITLE_MANAGER;
    private static Tab TAB_MANAGER;
    private static Bar BAR_MANAGER;
    private static UserManager USER_MANAGER;
    private static CommandManager COMMAND_MANAGER;
    public static PhantomEntityFactory getPhantomEntityFactory() {
        return PHANTOM_ENTITY_FACTORY;
    }

    public static Notification getNotificationManager() {
        return NOTIFICATION_MANAGER;
    }

    @NotImplement
    public static ScoreBoard getScoreboardManager() {
        return SCOREBOARD_MANAGER;
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

    @NotImplement
    public static Bar getBarManager() {
        return BAR_MANAGER;
    }

    public static UserManager getUserManager() {
        return USER_MANAGER;
    }

    public static CommandManager getCommandManager() {
        return COMMAND_MANAGER;
    }
}
