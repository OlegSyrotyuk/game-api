package net.villenium.game.api.phantom.entity;

public interface PhantomVariativeHologram extends PhantomVariativeEntity {

    /**
     * Получить текст голограммы в указанном варианте.
     *
     * @param variant название варианта.
     * @return текст голограммы для указанного варианта.
     */
    String getText(String variant);

    /**
     * Установить текст голограммы (все & будут заменены на цветовые коды) для указанного варианта.
     *
     * @param variant название варианта.
     * @param text    текст голограммы.
     */
    void setText(String variant, String text);

}
