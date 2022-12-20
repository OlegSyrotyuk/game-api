package net.stickmix.game.api.party;

import net.stickmix.game.api.GameApi;
import net.stickmix.game.api.annotation.NotImplement;
import net.stickmix.game.api.user.User;

import java.util.Collection;
import java.util.stream.Collectors;

@NotImplement
public interface Party {

    String getLeaderName();

    Collection<String> getMembersNames();

    default User getLeader() {
        return GameApi.getUserManager().get(getLeaderName());
    }

    default Collection<User> getMembers() {
        return getMembersNames().stream().map(GameApi.getUserManager()::get).collect(Collectors.toList());
    }

    int getMaxMemberCount();

}
