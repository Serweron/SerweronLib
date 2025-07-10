package pl.serweron.serweronLib.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.serweron.serweronLib.SerweronLib;

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
     * Whether the command must be executed by a player (not console).
     */
    @Getter
    @Setter
    private boolean notFromConsole = false;

    /**
     * Default usage pattern shown to the user.
     */
    private String usage = "<args>";

    /**
     * Message shown when a player lacks required permission.
     */
    private String permissionMessage = "";

    /**
     * Send a command usage to sender
     *
     * @param sender Command sender
     */
    private void sendUsage(CommandSender sender) {
        sender.sendMessage(usageMessage.replace("{usage}", usage));
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
        if (getPermission() != null) {
            if (sender.hasPermission(getPermission()) || sender.isOp()) {
                execute(sender, args);
            } else {
                sender.sendMessage(getPermissionMessage());
            }
        } else {
            if (!(sender instanceof Player) && notFromConsole) {
                sender.sendMessage(getNotConsole());
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
        return false;
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
}
