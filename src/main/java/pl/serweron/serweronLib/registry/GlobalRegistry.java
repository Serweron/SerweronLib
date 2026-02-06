package pl.serweron.serweronLib.registry;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.api.economy.managers.IEconomyManager;
import pl.serweron.serweronLib.api.ranks.managers.IRankManager;

public class GlobalRegistry {
    @Getter
    private static IRankManager rankManager;
    @Getter
    private static String rankMangerPluginName;
    @Getter
    private static IEconomyManager economyManager;
    @Getter
    private static String economyManagerPluginName;

    public static void modifyRegistry(GlobalRegistries registries, Object object, JavaPlugin plugin) {
        switch (registries) {
            case RANK:
                if (object instanceof IRankManager && plugin != null) {
                    rankManager = (IRankManager) object;
                    rankMangerPluginName = plugin.getName();
                } else {
                    throw new IllegalArgumentException("Invalid object passed to modifyRegistry");
                }
                break;
            case ECONOMY:
                if (object instanceof IEconomyManager && plugin != null) {
                    economyManager = (IEconomyManager) object;
                    economyManagerPluginName = plugin.getName();
                } else {
                    throw new IllegalArgumentException("Invalid object passed to modifyRegistry");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid object passed to modifyRegistry");
        }
    }

}
