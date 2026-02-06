package pl.serweron.serweronLib.command.context;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Getter
public class CommandContext {

    private final CommandSender sender;
    private final String[] args;
    private final String label;
    private final JavaPlugin plugin;

    private final Map<String, Object> cache = new HashMap<>();

    public CommandContext(
        CommandSender sender,
        String[] args,
        String label,
        JavaPlugin plugin
    ) {
        this.sender = sender;
        this.args = args;
        this.label = label;
        this.plugin = plugin;
    }

    /* =====================
       Sender helpers
       ===================== */

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public boolean isConsole() {
        return sender instanceof ConsoleCommandSender;
    }

    public Optional<Player> player() {
        return sender instanceof Player p ? Optional.of(p) : Optional.empty();
    }

    public Player requirePlayer() {
        if (!(sender instanceof Player p)) {
            throw new IllegalStateException("This command can only be executed by a player.");
        }
        return p;
    }

    /* =====================
       Argument helpers
       ===================== */

    public Optional<String> arg(int index) {
        return index >= 0 && index < args.length
            ? Optional.of(args[index])
            : Optional.empty();
    }

    public String argOr(int index, String def) {
        return arg(index).orElse(def);
    }

    public int argsLength() {
        return args.length;
    }

    /* =====================
       Permission helpers
       ===================== */

    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }

    public void requirePermission(String permission) {
        if (!hasPermission(permission)) {
            throw new IllegalStateException("You do not have permission to execute this command.");
        }
    }

    /* =====================
       Cache (performance)
       ===================== */

    @SuppressWarnings("unchecked")
    public <T> T cache(String key, Supplier<T> supplier) {
        return (T) cache.computeIfAbsent(key, k -> supplier.get());
    }

    /* =====================
       Reply helpers
       ===================== */

    public void reply(String message) {
        sender.sendMessage(message);
    }
}
