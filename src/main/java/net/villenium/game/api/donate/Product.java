package net.villenium.game.api.donate;

import net.villenium.game.api.user.User;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface Product {

    ItemStack getIcon();

    int getSlot();

    String getName();

    int getPrice();

    Consumer<User> getBuyHandler();

    Predicate<User> getAccessCheck();

}
