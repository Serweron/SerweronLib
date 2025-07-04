package pl.serweron.serweronLib.api.registry;

import lombok.Getter;
import lombok.Setter;
import pl.serweron.serweronLib.api.managers.IRankManager;

public class RankAPI {
    @Setter
    @Getter
    private static IRankManager rankManager;
}
