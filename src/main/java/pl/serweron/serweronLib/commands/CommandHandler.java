package pl.serweron.serweronLib.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Handles dynamic registration and management of custom plugin commands using {@link SCommand}.
 * Automatically sets permission messages, usage format, and console restrictions for each registered command.
 */
@AllArgsConstructor
public class CommandHandler {

    /** Reference to the owning plugin used for command namespacing and key generation. */
    private JavaPlugin plugin;

    /** Message shown when a user lacks permission to use a command. */
    private String permission_message;

    /**
     *  Default usage format string to be shown when command usage is incorrect.
     *  Variables in string: {usage} - usage (change this in Command usage property)
     */
    private String usage_format;

    /** Message displayed when a command is executed from console but requires a player. */
    private String not_console;

    /**
     * Internal registry of all registered commands.
     * @return command list
     */
    @Getter
    private final List<SCommand> commands = new ArrayList<>();

    /**
     * Registers a new command and applies default metadata (e.g., permission message, usage).
     * <p>
     * The command is registered using Bukkit's {@link CommandMap}.
     *
     * @param command the {@link SCommand} instance to register
     */
    public void registerCommand(SCommand command) {
        command.setPermissionMessage(permission_message);
        command.setUsage(usage_format.replace("{usage}", command.getUsage()));
        command.setNotConsole(not_console);

        Bukkit.getServer().getCommandMap().register(plugin.getName(), command);
        commands.add(command);
    }

    /**
     * Unregister command (if command has been register in the same instance CommandHandler)
     *
     * @param commandName name of command
     */
    public void unregisterCommand(String commandName) {
        SCommand command = getCommandByName(commandName);
        command.unregister(Bukkit.getServer().getCommandMap());
    }

    /**
     * Retrieves a registered command by its name (case-insensitive).
     *
     * @param commandName the name of the command to find
     * @return the {@link SCommand} instance, or {@code null} if not found
     */
    public SCommand getCommandByName(String commandName) {
        for (SCommand command : commands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                return command;
            }
        }
        return null;
    }
}
