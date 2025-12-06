package pl.serweron.serweronLib.registry;

import lombok.Getter;
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
    private static IEconomyManager economyManager;

    @Getter
    private static String pluginName;

    /**
     * Set economy manager
     *
     * @param economyManager economy manager class
     * @param pluginName name of your plugin
     */
    public static void setEconomyManager(IEconomyManager economyManager, String pluginName) {
        EconomyAPI.economyManager = economyManager;
        EconomyAPI.pluginName = pluginName;
    }
}
