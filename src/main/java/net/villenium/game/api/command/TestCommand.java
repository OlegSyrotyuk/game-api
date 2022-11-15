package net.villenium.game.api.command;

import org.bukkit.command.CommandSender;

@Command("test")
public class TestCommand {

    @CommandHandler
    public void handle(CommandSender sender, @Argument String name, @Argument int age) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    @Subcommand("pizda1")
    public void handlePizda1Subcommand(CommandSender sender, String[] args) {

    }

}
