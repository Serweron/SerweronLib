package pl.serweron.serweronLib.registry;

import lombok.Getter;
import lombok.Setter;
import pl.serweron.serweronLib.api.punishments.PunishmentManager;

/**
 * Central access point (Vault‑style) for the unified {@link PunishmentManager}.
 * <p>
 * External plugins can call this class to integrate with your punishment system
 * without depending on implementation details.
 */
public class PunishmentAPI {

    /**
     * The globally registered punishment manager.
     */
    @Getter @Setter
    private static PunishmentManager manager;

    /**
     * The name of the plugin providing the implementation.
     */
    @Getter
    private static String providerPluginName;

    /**
     * Registers the punishment manager.
     *
     * @param pluginName Name of the providing plugin.
     * @param manager Implementation instance of {@link PunishmentManager}.
     */
    public static void register(String pluginName, PunishmentManager manager) {
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
