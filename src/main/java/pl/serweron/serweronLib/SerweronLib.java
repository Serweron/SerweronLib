package pl.serweron.serweronLib;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class SerweronLib extends JavaPlugin {

    @Getter
    private static SerweronLib instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getLogger().info("SerweronLib is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
