package pl.serweron.serweronLib.registry;

import lombok.Getter;
import lombok.Setter;
import pl.serweron.serweronLib.api.punishments.IPunishmentManager;

/**
 * Central access point (Vault‑style) for the unified {@link IPunishmentManager}.
 * <p>
 * External plugins can call this class to integrate with your punishment system
 * without depending on implementation details.
 */
public class PunishmentAPI {

    /**
     * The globally registered punishment manager.
     */
    @Getter @Setter
    private static IPunishmentManager manager;

    /**
     * The name of the plugin providing the implementation.
     */
    @Getter
    private static String providerPluginName;

    /**
     * Registers the punishment manager.
     *
     * @param pluginName Name of the providing plugin.
     * @param manager Implementation instance of {@link IPunishmentManager}.
     */
    public static void register(String pluginName, IPunishmentManager manager) {
        PunishmentAPI.manager = manager;
        PunishmentAPI.providerPluginName = pluginName;
    }

    /**
     * Unregisters the current manager and clears references.
     * Should be called on plugin disable.
     */
    public static void unregister() {
        manager = null;
        providerPluginName = null;
    }
}
