package pl.serweron.serweronLib.langs;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
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
 * Create a "langs" folder in your resources and create files in pattern: {lang}_messages.yml
 * First lang in langs array in default activate lang
 */
public class MultiLangManager implements ILangManager {
    private JavaPlugin plugin;
    private String activeLanguage;
    @Getter
    private String[] langs;
    @Getter
    private FileConfiguration config;
    @Getter
    private YamlConfiguration defaultLanguageConfig;
    private File languageFile;

    public MultiLangManager(JavaPlugin plugin, String[] langs) {
        this.plugin = plugin;
        this.langs = langs;
        this.activeLanguage = langs[0];
        reload();
    }

    @Override
    public String getLangName() {
        return activeLanguage;
    }

    /**
     * @param key Message key
     * @return Message
     */
    @Override
    public String getMessage(String key) {
        String message;
        if (!config.contains(key)) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't find key: " + key);
            if (defaultLanguageConfig.contains(key)) {
                message = defaultLanguageConfig.getString(key);
            } else {
                return "Error! Couldn't find key: " + key;
            }
        } else {
            message = config.getString(key);
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
        if (!config.contains(key)) {
            plugin.getLogger().log(Level.SEVERE, "Couldn't find key: " + key);
            if (defaultLanguageConfig.contains(key)) {
                message = defaultLanguageConfig.getString(key);
            }
        } else {
            message = config.getString(key);
        }
        return ChatColor.translate('&', message);
    }

    public void setActiveLanguage(String activeLanguage) {
        this.activeLanguage = activeLanguage;
        reload();

    }

    @Override
    public void reload() {
        languageFile = new File(plugin.getDataFolder(), activeLanguage + "_messages.yml");

        if (!languageFile.exists()) {
            plugin.saveResource( "langs/" + activeLanguage + "_messages.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(languageFile);

        try (InputStream defaultStream = plugin.getResource(activeLanguage + "_messages.yml")) {
            if (defaultStream != null) {
                defaultLanguageConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            }
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not load default language file from jar!", e);
        }
    }
}
