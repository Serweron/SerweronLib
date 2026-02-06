package pl.serweron.serweronLib.langs;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.api.managers.ILangManager;
import pl.serweron.serweronLib.ui.colors.ChatColor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;


/**
 * Represents multi langs system in your plugin
 * Usage:
 * <p>
 * Create a messages.yml file in resources
 */
public class SingleLangManager implements ILangManager {

    private YamlConfiguration languageConfig;
    private YamlConfiguration defaultLanguageConfig;
    private File languageFile;
    private JavaPlugin plugin;
    private String lang;

    public SingleLangManager(JavaPlugin plugin, String lang) {
        this.plugin = plugin;
        this.lang = lang;
        languageFile = new File(plugin.getDataFolder(), "messages.yml");

        if (!languageFile.exists()) {
            plugin.saveResource("messages.yml", false);
        }

        languageConfig = YamlConfiguration.loadConfiguration(languageFile);

        try (InputStream defaultStream = plugin.getResource("messages.yml")) {
            if (defaultStream != null) {
                defaultLanguageConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not load default language file from jar!", e);
        }
    }

    @Override
    public String getLangName() {
        return lang;
    }

    /**
     * @param key Message key
     * @return Message
     */
    @Override
    public String getMessage(String key) {
        String message;
        if (!languageConfig.contains(key)) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't find key: " + key);
           if (defaultLanguageConfig.contains(key)) {
               message = defaultLanguageConfig.getString(key);
           } else {
               return "Error! Couldn't find key: " + key;
           }
        } else {
            message = languageConfig.getString(key);
        }
        return ChatColor.translate('&', message);
    }

    /**
     * @param key Message key
     * @param defaultValue Default message
     * @return Message
     */
    @Override
    public String getMessage(String key, String defaultValue) {
        String message = defaultValue;
        if (!languageConfig.contains(key)) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't find key: " + key);
            if (defaultLanguageConfig.contains(key)) {
                message = defaultLanguageConfig.getString(key);
            }
        } else {
            message = languageConfig.getString(key);
        }
        return ChatColor.translate('&', message);
    }

    public void reload() {
        languageConfig = YamlConfiguration.loadConfiguration(languageFile);
    }

}
