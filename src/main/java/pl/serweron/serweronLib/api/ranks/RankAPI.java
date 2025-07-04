package pl.serweron.serweronLib.api.ranks;

import lombok.Getter;
import lombok.Setter;

public class RankAPI {
    @Setter
    @Getter
    private static IRankManager rankManager;
}
