package pl.serweron.serweronLib.langs;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.colors.ChatColor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class LangManager {

    private YamlConfiguration languageConfig;
    private YamlConfiguration defaultLanguageConfig;
    private File languageFile;
    private JavaPlugin plugin;
    private String prefix = ChatColor.translate('&',"&7[&6Serweron&7]");

    public LangManager(JavaPlugin plugin) {
        this.plugin = plugin;
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

    public LangManager(JavaPlugin plugin, String prefix) {
        this(plugin);
        this.prefix = ChatColor.translate('&', prefix);
    }

    /**
     * @param key Message key
     * @return Message
     */
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
     * @return Message
     */
    public String getMessageWithPrefix(String key) {
        return prefix + " " + getMessage(key);
    }

    public void reload() {
        languageConfig = YamlConfiguration.loadConfiguration(languageFile);
    }

}