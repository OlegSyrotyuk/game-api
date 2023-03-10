package net.villenium.game.api.menu.template;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.villenium.game.api.GameApi;
import net.villenium.game.api.item.GameItemStack;
import net.villenium.game.api.item.GameItemStackMetaBuilder;
import net.villenium.game.api.menu.Menu;
import net.villenium.game.api.menu.MenuButton;
import net.villenium.game.api.menu.MenuEmptyButton;
import net.villenium.game.api.user.User;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ConfirmMenu {

    /**
     * Действие которое, произойдет после подтверждения.
     */
    @Getter
    private final Consumer<User> doIfConfirm;
    @Setter
    private String title = "Подтверждение";
    @Setter
    private ItemStack icon;

    public void open(User user) {
        ArrayList<Integer> confirmSlots = Lists.newArrayList(0, 1, 2, 3, 9, 10, 11, 12, 18, 19, 20, 21);
        ArrayList<Integer> cancelSlots = Lists.newArrayList(5, 6, 7, 8, 14, 15, 16, 17, 23, 24, 25, 26);
        ArrayList<Integer> emptySlots = Lists.newArrayList(4, 13, 22);
        Menu menu = GameApi.getMenuUtil().create(title, 3);
        MenuButton confirmButton = new MenuButton(new GameItemStack(Material.STAINED_GLASS_PANE, "&a&lПодтвердить", new GameItemStackMetaBuilder().materialData(13).build())) {
            @Override
            public void onClick(Player player, ClickType clickType, int slot) {
                doIfConfirm.accept(user);
                player.closeInventory();
            }
        };
        MenuButton cancelButton = new MenuButton(new GameItemStack(Material.STAINED_GLASS_PANE, "&c&lОтклонить", new GameItemStackMetaBuilder().materialData(14).build())) {
            @Override
            public void onClick(Player player, ClickType clickType, int slot) {
                player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1F, 1F);
                player.closeInventory();
            }
        };
        MenuEmptyButton emptyButton = new MenuEmptyButton(new GameItemStack(Material.STAINED_GLASS_PANE, "", new GameItemStackMetaBuilder().materialData(15).build()));
        confirmSlots.forEach(integer -> menu.addItem(confirmButton, integer));
        cancelSlots.forEach(integer -> menu.addItem(cancelButton, integer));
        emptySlots.forEach(integer -> menu.addItem(emptyButton, integer));
        if (icon != null) {
            menu.addItem(new MenuEmptyButton(icon), 13);
        }
        menu.open(user);
    }

}
