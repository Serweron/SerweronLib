package pl.serweron.serweronLib;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.commands.CommandHandler;

public final class SerweronLib extends JavaPlugin {

    @Getter
    private static SerweronLib instance;

    @Getter
    private CommandHandler commandHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        commandHandler = new CommandHandler(this, "&eYou do not have permission to execute this command.", "&cUsage: {usage}", "&eYou cannot execute this command from the console.");
        getLogger().info("SerweronLib is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
