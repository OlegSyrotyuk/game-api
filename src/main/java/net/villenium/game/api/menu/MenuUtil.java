package net.villenium.game.api.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public interface MenuUtil {

    /**
     * Создать новое меню.
     *
     * @param name        название меню (то, что будет отображаться названием инвентаря игрокам).
     *                    Все цветовые коды будут заменены.
     * @param sizeInLines размер инвентаря в строках
     * @return созданное меню.
     * @throws IllegalArgumentException если размер меньше 1 или больше 6.
     */
    default Menu create(String name, int sizeInLines) throws IllegalArgumentException {
        return create(name, sizeInLines, null, null);
    }

    /**
     * Создать новое меню.
     *
     * @param name        название меню (то, что будет отображаться названием инвентаря игрокам).
     *                    Все цветовые коды будут заменены.
     * @param sizeInLines размер инвентаря в строках
     * @param onOpen      консумер, который будет выполняться при открытии этого меню для игрока.
     * @return созданное меню.
     * @throws IllegalArgumentException если размер меньше 1 или больше 6.
     */
    default Menu create(String name, int sizeInLines, BiConsumer<Menu, Player> onOpen) throws IllegalArgumentException {
        return create(name, sizeInLines, onOpen, null);
    }

    /**
     * Создать новое меню.
     *
     * @param name        название меню (то, что будет отображаться названием инвентаря игрокам).
     *                    Все цветовые коды будут заменены.
     * @param sizeInLines размер инвентаря в строках
     * @param onOpen      консумер, который будет выполняться при открытии этого меню для игрока.
     * @param onClose     консумер, который будет выполняться при закрытии этого меню у игрока.
     * @return созданное меню.
     * @throws IllegalArgumentException если размер меньше 1 или больше 6.
     */
    Menu create(String name, int sizeInLines, BiConsumer<Menu, Player> onOpen, BiConsumer<Menu, Player> onClose) throws IllegalArgumentException;

    /**
     * Запретить выкидывать и как-либо перемещать указанный предмет.
     * Подходящим под условия этого предмета считаются все предметы с его
     * материалом и метадатой материала, а также с тем же названием.
     *
     * @param itemStack предмет.
     */
    void blockItemStack(ItemStack itemStack);

    /**
     * Разблокировать указанный предмет.
     *
     * @param itemStack предмет.
     * @see MenuUtil#blockItemStack(ItemStack)
     */
    void unblockItemStack(ItemStack itemStack);

}
