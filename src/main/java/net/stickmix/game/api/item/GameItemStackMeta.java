package net.stickmix.game.api.item;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class GameItemStackMeta {

    int amount = 1;
    short durability;
    Color leatherArmorColor;
    final Map<Enchantment, Integer> enchantments = new HashMap<>();
    final Set<ItemFlag> flags = EnumSet.noneOf(ItemFlag.class);

    GameItemStackMeta() {}

}
