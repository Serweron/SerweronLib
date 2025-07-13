package pl.serweron.serweronLib.langs;

import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.api.managers.ILangManager;

/**
 * Factory of Language Managers
 * Options:
 * SingleLangManager
 * MultiLangManager
 */
public class LangFactory {
    /**
     * Create a Language Manager
     *
     * @param langType Manager type
     * @param langs Langs list (If you use single, first is activated lang)
     * @param plugin JavaPlugin
     * @return Language Manager
     */
    public static ILangManager createLangManager(LangType langType, String[] langs, JavaPlugin plugin) {
        ILangManager langManager = switch (langType) {
            case SINGLE -> new SingleLangManager(plugin, langs[0]);
            case MULTI -> new MultiLangManager(plugin, langs);
        };

        return langManager;
    }

}
