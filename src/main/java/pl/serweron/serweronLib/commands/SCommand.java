package pl.serweron.serweronLib.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.serweron.serweronLib.SerweronLib;
import pl.serweron.serweronLib.commands.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * Abstract base class for custom commands in the plugin.
 * <p>
 * Provides built-in handling for permissions, console restrictions, error reporting,
 * and tab completion. Extend this class to define command logic using the {@link #execute} method.
 */
public abstract class SCommand extends Command {

    /**
     * Message shown when a command cannot be executed from the console.
     */
    @Getter
    @Setter
    private String notConsole = "";

    /**
     * Message shown when a player lacks required permission.
     */
    private String permissionMessage = "";

    /**
     * Constructs a new {@code SCommand} instance.
     *
     * @param command     the command label (e.g., "home")
     */
    protected SCommand(String command) {
        super(command);

        // CommandData annotation is used to set command metadata like aliases, description, and permission
        if (getClass().isAnnotationPresent(CommandData.class)) {
            CommandData commandData = getClass().getAnnotation(CommandData.class);
            if (commandData == null) return;
            setAliases(Arrays.asList(commandData.aliases()));
            setDescription(commandData.description());
            setPermission(commandData.permission());
        }
    }

    /**
     * Constructs a new {@code SCommand} instance.
     *
     * @param command     the command label (e.g., "home")
     * @param aliases     an array of alias names for the command
     * @param description short description of the command's purpose
     * @param permission  the required permission to execute the command, or empty for none
     */
    protected SCommand(String command, String[] aliases, String description, String permission) {
        super(command);
        setAliases(Arrays.asList(aliases));
        setDescription(description);
        if (!permission.isEmpty()) {
            setPermission(permission);
        }
    }

    /**
     * Executes the command logic, handling permission and sender restrictions.
     *
     * @param sender the command sender (player or console)
     * @param label  the command label used
     * @param args   the arguments passed with the command
     * @return {@code true} if handled, {@code false} otherwise
     */
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player) && this.getClass().isAnnotationPresent(NotFromConsole.class)) {
            sender.sendMessage(getNotConsole());
            return false;
        }

        if (getPermission() != null) {
            if (sender.hasPermission(getPermission()) || sender.isOp()) {
                if (args.length < getDefaultNumberOfArgs()) {
                    sendUsage(sender);
                    return false;
                }

                execute(sender, args);
            } else {
                sender.sendMessage(getPermissionMessage());
            }
        } else {
            if (args.length < getDefaultNumberOfArgs()) {
                sendUsage(sender);
                return false;
            }

            try {
                execute(sender, args);
            } catch (Exception e) {
                sender.sendMessage("Error executing command. Please contact the developer.");
                SerweronLib.getInstance().getLogger().log(
                        Level.WARNING,
                        "Exception while executing " + label + ": " + e.getMessage()
                );
            }
        }
        return true;
    }
    private int getDefaultNumberOfArgs() {
        Class<?> clazz = this.getClass();
        if (clazz.isAnnotationPresent(DefaultNumberOfArgs.class)) {
            DefaultNumberOfArgs annotation = (DefaultNumberOfArgs) clazz.getAnnotation(DefaultNumberOfArgs.class);
            return annotation.number();
        }
        return 0;
    }

    /**
     * Main logic to be implemented for the command.
     *
     * @param sender the command sender (player or console)
     * @param args   the arguments passed with the command
     */
    public abstract void execute(@NotNull CommandSender sender, String[] args);

    /**
     * Handles tab completion for the command.
     *
     * @param sender the command sender
     * @param alias  the alias used for the command
     * @param args   the current command arguments
     * @return a list of possible completions, or an empty list
     * @throws IllegalArgumentException if an invalid argument is passed
     */
    @Override
    public @NotNull List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    /**
     * Abstract method for providing tab completion suggestions.
     *
     * @param sender the command sender
     * @param args   the current arguments passed to the command
     * @return list of suggestions for tab completion
     */
    @NotNull
    public abstract List<String> onTabComplete(@NotNull CommandSender sender, String[] args);

    /**
     * Send a command usage to sender
     *
     * @param sender Command sender
     */
    protected void sendUsage(CommandSender sender) {
        Class<?> clazz = this.getClass();

        String usageRaw = "/" + getName() + " ";
        if (clazz.isAnnotationPresent(Usage.class)) {
            usageRaw += clazz.getAnnotation(Usage.class).usage();
        }

        String messageTemplate = (usageMessage == null || usageMessage.isEmpty())
                ? "Usage: {usage}" : usageMessage;

        sender.sendMessage(messageTemplate.replace("{usage}", usageRaw));
    }


    /**
     * Sends a permission message to the command sender.
     * If no custom message is set, it defaults to a generic permission error.
     *
     * @param sender the command sender
     */
    protected void sendPermissionMessage(CommandSender sender) {
        if (permissionMessage == null || permissionMessage.isEmpty()) {
            sender.sendMessage("You do not have permission to use this command.");
        } else {
            sender.sendMessage(permissionMessage);
        }
    }
}
