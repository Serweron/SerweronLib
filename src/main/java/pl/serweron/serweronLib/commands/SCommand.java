package pl.serweron.serweronLib.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import pl.serweron.serweronLib.SerweronLib;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public abstract class SCommand extends Command {
    @Getter
    @Setter
    private String notConsole = "";

    @Getter
    @Setter
    private boolean notFromConsole = false;

    private String usage = "<args>";
    private String permissionMessage = "";

    protected SCommand(String command, String[] aliases, String description, String permission) {
        super(command);
        setAliases(Arrays.asList(aliases));
        setDescription(description);
        if (permission != "") {
            setPermission(permission);
        }
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (getPermission() != null) {
            if (sender.hasPermission(getPermission()) || sender.isOp()) {
                execute(sender, strings);
            } else {
                sender.sendMessage(getPermissionMessage());
            }
        } else {
            if (!(sender instanceof Player) && notFromConsole) {
                sender.sendMessage(getNotConsole());
                return false;
            }

            try {
                execute(sender,strings);
            } catch (Exception e) {
                sender.sendMessage("Error executing command. Please contact the developer.");
                SerweronLib.getInstance().getLogger().log(Level.WARNING, "Exception while executing " + s + ": " + e.getMessage());
            }

        }
        return false;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}

