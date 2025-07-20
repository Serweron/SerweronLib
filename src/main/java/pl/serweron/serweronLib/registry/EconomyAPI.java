package pl.serweron.serweronLib.registry;

import lombok.Getter;
import lombok.Setter;
import pl.serweron.serweronLib.api.economy.managers.IEconomyManager;

/**
 * Provides static access to the {@link IEconomyManager} instance.
 * <p>
 * This class acts as a central registry for the economy system API, allowing
 * other components and external plugins to retrieve the currently active economy manager.
 * <p>
 * The instance must be set during plugin initialization (e.g., via dependency injection).
 */
public class EconomyAPI {
    /**
     * The globally accessible {@link IEconomyManager} instance used for economy management.
     */
    @Getter
    @Setter
    private static IEconomyManager rankManager;
}