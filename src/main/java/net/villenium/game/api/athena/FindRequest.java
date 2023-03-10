package net.villenium.game.api.athena;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindRequest {

    private String conditions;
    private int count;
}
