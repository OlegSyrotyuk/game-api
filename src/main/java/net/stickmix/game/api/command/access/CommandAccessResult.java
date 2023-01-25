package net.stickmix.game.api.command.access;

import lombok.Getter;
import net.stickmix.game.api.user.permission.PermissionGroup;
import net.stickmix.game.api.util.ChatUtil;

@Getter
public class CommandAccessResult {

    private PermissionGroup group;
    private String message;

    public CommandAccessResult(PermissionGroup group) {
        this.group = group;
    }

    public CommandAccessResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        if (group != null) {
            return ChatUtil.colorize("&fДля этого действия необходим статус %s&f.", group.getName());
        }
        return message;
    }

}
