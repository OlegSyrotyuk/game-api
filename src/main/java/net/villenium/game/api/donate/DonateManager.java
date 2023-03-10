package net.villenium.game.api.donate;

public interface DonateManager {

    /**
     * Получить билдер товаров.
     *
     * @param name имя товара.
     * @return билдер товаров.
     */
    ProductBuilder newProduct(String name);

}
