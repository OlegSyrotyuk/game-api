package net.villenium.game.api.menu.template;

import lombok.RequiredArgsConstructor;
import net.villenium.game.api.menu.MenuItem;
import net.villenium.game.api.user.User;

import java.util.List;

@RequiredArgsConstructor
public class PagedMenu {

    private final String title;
    private final List<MenuItem> items;

    public void open(User user) {

    }

}
