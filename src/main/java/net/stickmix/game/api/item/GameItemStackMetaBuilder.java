package net.stickmix.game.api.item;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

public class GameItemStackMetaBuilder {

    private final GameItemStackMeta meta = new GameItemStackMeta();

    public GameItemStackMetaBuilder amount(int amount) {
        this.meta.amount = amount;
        return this;
    }

    public GameItemStackMetaBuilder durability(int durability) {
        this.meta.durability = (short) durability;
        return this;
    }

    public GameItemStackMetaBuilder materialData(int materialData) {
        return durability(materialData);
    }

    public GameItemStackMetaBuilder leatherArmorColor(Color color) {
        this.meta.leatherArmorColor = color;
        return this;
    }

    public GameItemStackMetaBuilder enchantment(Enchantment enchantment, int level) {
        this.meta.enchantments.put(enchantment, level);
        return this;
    }

    public GameItemStackMetaBuilder flag(ItemFlag flag) {
        this.meta.flags.add(flag);
        return this;
    }

    public GameItemStackMeta build() {
        return this.meta;
    }

}
