package pl.serweron.serweronLib.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CommandHandler {
    private JavaPlugin plugin;
    private String permission_message;
    private String usage_format;
    private String not_console;

    @Getter
    private final List<SCommand> commands = new ArrayList<SCommand>();

    public void registerCommand(SCommand command) {
        command.setPermissionMessage(permission_message);
        command.setUsage(usage_format);
        command.setNotConsole(not_console);

        Bukkit.getServer().getCommandMap().register(plugin.getName(), command);
        commands.add(command);
    }

    public SCommand getCommandByName(String commandName) {
            for (SCommand command : commands) {
                if (command.getName().equalsIgnoreCase(commandName)) {
                    return command;
                }
            }
            return null;
    }

}
