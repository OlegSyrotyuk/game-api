package net.villenium.game.api.item;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameItemStack extends ItemStack {

    /**
     * Создать новый ItemStack с указанным материалом, именем, описанием и метой.
     * Количество предметов в стаке будет равным 1.
     *
     * @param material    материал.
     * @param name        имя (цветовые коды будут заменены).
     * @param description построчное описание (цветовые коды будут заменены).
     * @param meta        мета предмета. Создается через DmsItemStackMetaBuilder.
     */
    public GameItemStack(Material material, String name, List<String> description, GameItemStackMeta meta) {
        this(material, 1, name, description, meta);
    }

    /**
     * Создать новый ItemStack с указанным материалом, именем и количеством.
     * Описание предмета будет пустым.
     *
     * @param material материал.
     * @param name     имя (цветовые коды будут заменены).
     * @param amount   количество предмета.
     */
    public GameItemStack(Material material, int amount, String name) {
        this(material, amount, name, Collections.emptyList(), null);
    }

    /**
     * Создать новый ItemStack с указанным материалом и именем.
     * Количество предмета в стаке будет равным 1. Описание будет пустым.
     *
     * @param material материал.
     * @param name     имя (цветовые коды будут заменены).
     */
    public GameItemStack(Material material, String name) {
        this(material, name, Collections.emptyList());
    }

    /**
     * Создать новый ItemStack с указанным материалом, именем и описанием.
     * Количество предмета в стаке будет равным 1.
     *
     * @param material    материал.
     * @param name        имя (цветовые коды будут заменены).
     * @param description построчное описание (цветовые коды будут заменены).
     */
    public GameItemStack(Material material, String name, List<String> description) {
        this(material, name, description, null);
    }

    /**
     * Создать новый ItemStack с указанным материалом, именем и метой.
     * Количество предмета в стаке будет равным 1. Описание будет пустым.
     *
     * @param material материал.
     * @param name     имя (цветовые коды будут заменены).
     * @param meta     мета предмета. Создается через DmsItemStackMetaBuilder.
     */
    public GameItemStack(Material material, String name, GameItemStackMeta meta) {
        this(material, name, Collections.emptyList(), meta);
    }

    /**
     * Создать новый ItemStack с указанным материалом, именем, описанием и метой.
     *
     * @param material    материал.
     * @param name        имя (цветовые коды будут заменены).
     * @param amount      количество предмета.
     * @param description построчное описание (цветовые коды будут заменены).
     * @param meta        мета предмета. Создается через DmsItemStackMetaBuilder.
     */
    public GameItemStack(Material material, int amount, String name, List<String> description, GameItemStackMeta meta) {
        super(material, amount);
        ItemMeta im = getItemMeta();
        im.setDisplayName(colorize("&e%s", name));
        setItemMeta(im);
        if (description != null && !description.isEmpty()) {
            im.setLore(description.stream().map(GameItemStack::colorize).collect(Collectors.toList()));
            setItemMeta(im);
        }
        if (meta == null) {
            return;
        }
        if (meta.amount != amount) {
            setAmount(meta.amount);
        }
        if (meta.durability != 0) {
            setDurability(meta.durability);
        }
        if (!meta.enchantments.isEmpty()) {
            addUnsafeEnchantments(meta.enchantments);
        }
        if (!meta.flags.isEmpty()) {
            applyFlags(meta.flags.toArray(new ItemFlag[0]));
        }
        if (meta.leatherArmorColor != null) {
            Preconditions.checkState(im instanceof LeatherArmorMeta, "This itemstack is not a leather armor item");
            LeatherArmorMeta lam = (LeatherArmorMeta) im;
            lam.setColor(meta.leatherArmorColor);
            setItemMeta(lam);
        }
    }

    /**
     * Применить указанные флаги к этому предмету.
     *
     * @param flags флаги.
     * @return этот предмет.
     */
    public GameItemStack applyFlags(ItemFlag... flags) {
        ItemMeta im = getItemMeta();
        im.addItemFlags(flags);
        setItemMeta(im);
        return this;
    }

    /**
     * Установить неразрушимость этого предмета.
     *
     * @param value неразрушим = true/разрушим = false.
     * @return этот предмет.
     */
    public GameItemStack setUnbreakable(boolean value) {
        ItemMeta im = getItemMeta();
        im.setUnbreakable(value);
        setItemMeta(im);
        return this;
    }

    private static String colorize(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    private static String colorize(String string, Object... args) {
        return colorize(String.format(string, args));
    }

}