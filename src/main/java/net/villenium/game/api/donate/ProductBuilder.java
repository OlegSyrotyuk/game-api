package net.villenium.game.api.donate;

import net.villenium.game.api.user.User;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ProductBuilder {

    /**
     * Установить иконку для продукта.
     *
     * @param icon иконка продукта.
     * @return этот билдер.
     */
    ProductBuilder icon(ItemStack icon);

    /**
     * Слот в котором будет, находится продукт.
     *
     * @param slot номер слота.
     * @return этот билдер.
     */
    ProductBuilder slot(int slot);

    /**
     * Установить цену товара.
     *
     * @param price цена.
     * @return этот билдер.
     */
    ProductBuilder price(int price);

    /**
     * Действие которое, будет выполнено после покупки.
     *
     * @param userConsumer действие.
     * @return этот билдер.
     */
    ProductBuilder handle(Consumer<User> userConsumer);

    /**
     * Проверка при которой покупка товара невозможна.
     *
     * @param userPredicate проверка.
     * @return этот билдер.
     */
    ProductBuilder access(Predicate<User> userPredicate);

    /**
     * Добавить продукт в список услуг режима.
     */
    void register();

}
