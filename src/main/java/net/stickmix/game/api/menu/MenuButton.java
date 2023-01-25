package net.stickmix.game.api.menu;

import net.stickmix.game.api.item.GameItemStack;
import net.stickmix.game.api.item.GameItemStackMetaBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class MenuButton extends MenuItem {

    /**
     * Создать кнопку для меню с указанным материалом, названием и описанием.
     *
     * @param icon        материал.
     * @param name        названием (цветовые коды будут заменены).
     * @param description построчное описание (цветовые коды будут заменены).
     */
    public MenuButton(Material icon, String name, List<String> description) {
        super(new GameItemStack(icon, "&a" + name, description).applyFlags(ItemFlag.values()));
    }

    /**
     * Создать кнопку для меню с указанным материалом, датой материала, названием и описанием.
     *
     * @param icon        материал.
     * @param data        дата материала (например, 0-15 у шерсти).
     * @param name        названием (цветовые коды будут заменены).
     * @param description построчное описание (цветовые коды будут заменены).
     */
    public MenuButton(Material icon, int data, String name, List<String> description) {
        super(new GameItemStack(icon, "&a" + name, description, new GameItemStackMetaBuilder().durability(data).build()).applyFlags(ItemFlag.values()));
    }

    /**
     * Поддержка базового конструктора.
     *
     * @param itemStack иконка кнопки для меню.
     */
    public MenuButton(ItemStack itemStack) {
        super(itemStack);
    }

}
