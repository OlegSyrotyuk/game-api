package net.villenium.game.api.command;

import net.villenium.game.api.user.permission.PermissionGroup;
import org.bukkit.command.CommandSender;

@Command(name = "test")
@Group(group = PermissionGroup.ADMINISTRATOR)
public class TestCommand {

    @CommandHandler
    public void handle(CommandSender sender, @Argument String name, @Argument int age) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    @Subcommand(name = "pizda1")
    @Group(group = PermissionGroup.HEAD_ADMIN)
    public void handlePizda1Subcommand(CommandSender sender, String[] args) {

    }

}
