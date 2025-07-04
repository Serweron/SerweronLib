package pl.serweron.serweronLib.utils;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Config {
    protected Logger logger = Logger.getLogger(Config.class.getName());
    @Getter
    protected FileConfiguration config;
    protected File file;

    public Config(File file) {
        this.file = file;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Could not create file: \n$file", e);
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            logger.severe("Could not save config file: " + file.getName());
            e.printStackTrace();
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

}