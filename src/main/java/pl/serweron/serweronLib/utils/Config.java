package pl.serweron.serweronLib.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class for custom configs in Yaml
 */
public abstract class Config {
    protected Logger logger = Logger.getLogger(Config.class.getName());
    @Getter
    protected FileConfiguration config;
    protected File file;


    /**
     * Initializete config file
     *
     * @param file config file
     * @param resourcePath resource path
     * @param plugin Main class of plugin
     */
    public void init(File file, String resourcePath, JavaPlugin plugin) {
        this.file = file;
        if (!file.exists()) {
            plugin.saveResource(resourcePath, false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Save config file
     */
    public void save() {
        if (config == null) throw new NullPointerException("Config is null");

        try {
            config.save(file);
        } catch (IOException e) {
            logger.severe("Could not save config file: " + file.getName());
            e.printStackTrace();
        }
    }

    /**
     * Reload config
     */
    public void reload() {
        if (config == null) throw new NullPointerException("Config is null");
        this.config = YamlConfiguration.loadConfiguration(file);
    }

}