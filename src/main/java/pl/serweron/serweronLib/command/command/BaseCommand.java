package pl.serweron.serweronLib.command.command;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import pl.serweron.serweronLib.command.context.CommandContext;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand extends Command {
    /* =====================
       General
       ===================== */
    private final JavaPlugin plugin;



    /* =====================
       Metadata (runtime)
       ===================== */

    @Getter
    @Setter
    private boolean playerOnly;

    @Getter
    @Setter
    private boolean consoleOnly;

    @Getter
    @Setter
    private String permissionMsg = "You do not have permission to use this command.";

    @Getter
    @Setter
    private String consoleOnlyMsg = "This command can only be executed from console.";

    @Getter
    @Setter
    private String playerOnlyMsg = "This command can only be executed by a player.";

    @Setter
    private TabComplete tabCompleter;

    /* =====================
       Constructors
       ===================== */

    protected BaseCommand(@NotNull String name, JavaPlugin plugin) {
        super(name);
        this.plugin = plugin;
    }

    /* =====================
       Bukkit entrypoint
       ===================== */

    @Override
    public final boolean execute(
        @NotNull CommandSender sender,
        @NotNull String label,
        @NotNull String[] args
    ) {

        /* Sender validation */
        if (!validateSender(sender)) {
            return true;
        }

        /* Permission validation */
        if (!validatePermission(sender)) {
            sender.sendMessage(permissionMsg);
            return true;
        }

        /* Execution */
        try {
            CommandContext ctx = new CommandContext(sender, args, label, plugin);
            execute(ctx);
        } catch (CommandException e) {
            sender.sendMessage(e.getMessage());
        } catch (Exception e) {
            sender.sendMessage("An internal error occurred while executing this command.");
            throw new CommandException(e.getMessage());
        }

        return true;
    }

    /* =====================
       Tab complete
       ===================== */

    @Override
    public final @NotNull List<String> tabComplete(
        @NotNull CommandSender sender,
        @NotNull String label,
        @NotNull String[] args
    ) {
        try {
            return tabCompleter.handle(sender, this, label, args);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    /* =====================
       Validation
       ===================== */

    protected boolean validateSender(CommandSender sender) {

        if (playerOnly && !(sender instanceof Player)) {
            sender.sendMessage(playerOnlyMsg);
            return false;
        }

        if (consoleOnly && !(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(consoleOnlyMsg);
            return false;
        }

        return true;
    }

    protected boolean validatePermission(CommandSender sender) {
        if (getPermission() == null || getPermission().isEmpty()) {
            return true;
        }
        return sender.hasPermission(getPermission()) || sender.isOp();
    }

    /* =====================
       API for developers
       ===================== */

    /**
     * Main command logic.
     */
    protected abstract void execute(@NotNull CommandContext context);
}
