package pl.serweron.serweronLib.registry;

import lombok.Getter;
import lombok.Setter;
import pl.serweron.serweronLib.api.ranks.managers.IRankManager;

/**
 * Provides static access to the {@link IRankManager} instance.
 * <p>
 * This class acts as a central registry for the rank system API, allowing
 * other components and external plugins to retrieve the currently active rank manager.
 * <p>
 * The instance must be set during plugin initialization (e.g., via dependency injection).
 */
public class RankAPI {

    /**
     * The globally accessible {@link IRankManager} instance used for rank management.
     */
    @Getter
    @Setter
    private static IRankManager rankManager;
}
