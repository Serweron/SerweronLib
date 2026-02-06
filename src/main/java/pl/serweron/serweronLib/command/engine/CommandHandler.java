package pl.serweron.serweronLib.command.engine;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.command.command.BaseCommand;
import pl.serweron.serweronLib.command.command.CommandMapper;
import pl.serweron.serweronLib.command.command.CommandMapper.MappedCommand;
import pl.serweron.serweronLib.ui.colors.ChatColor;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Central command registry and bootstrapper.
 *
 * Responsibilities:
 * - mapping annotations to metadata
 * - applying metadata to BaseCommand
 * - registering / unregistering commands
 *
 * This class is the ONLY place that touches CommandMap.
 */
public class CommandHandler {

    private final JavaPlugin plugin;
    private final CommandMap commandMap;

    @Getter
    private final List<BaseCommand> commands = new ArrayList<>();

    @Getter
    private final Map<String, MappedCommand> metadata = new HashMap<>();

    @Getter
    private final TabCompleteDispatcher tabCompleteDispatcher;


    /* =====================
       Global defaults
       ===================== */

    private String permissionMessage =
        "&cYou do not have permission to use this command.";

    private String playerOnlyMessage =
        "&cThis command can only be executed by a player.";

    private String consoleOnlyMessage =
        "&cThis command can only be executed from console.";

    /* =====================
       Constructor
       ===================== */

    public CommandHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.commandMap = resolveCommandMap();
        this.tabCompleteDispatcher = new TabCompleteDispatcher(metadata, plugin);
    }

    /* =====================
       Registration
       ===================== */

    public void register(BaseCommand command) {

        MappedCommand meta =
            CommandMapper.mapCommand(command.getClass());

        applyMetadata(command, meta);

        commandMap.register(plugin.getName(), command);


        metadata.put(command.getName(), meta);
        commands.add(command);

        command.setTabCompleter((sender, cmd, label, args) -> this.tabCompleteDispatcher.complete(cmd, sender, label, args));
    }

    public void unregister(String name) {
        BaseCommand cmd = getCommand(name);
        if (cmd != null) {
            cmd.unregister(commandMap);
            commands.remove(cmd);
        }
    }

    /* =====================
       Metadata application
       ===================== */

    private void applyMetadata(BaseCommand command, MappedCommand meta) {

        if (meta.getAliases().length > 0 && command.getAliases().isEmpty()) {
            command.setAliases(List.of(meta.getAliases()));
        }

        if (meta.getDescription() != null &&
            (command.getDescription() == null || command.getDescription().isEmpty())) {
            command.setDescription(meta.getDescription());
        }

        if (meta.getPermission() != null && command.getPermission() == null) {
            command.setPermission(meta.getPermission());
        }

        command.setPlayerOnly(meta.isPlayerOnly());
        command.setConsoleOnly(meta.isConsoleOnly());

        command.setPermissionMsg(color(permissionMessage));
        command.setPlayerOnlyMsg(color(playerOnlyMessage));
        command.setConsoleOnlyMsg(color(consoleOnlyMessage));
    }

    /* =====================
       Lookup
       ===================== */

    public BaseCommand getCommand(String name) {
        for (BaseCommand cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd;
            }
            if (cmd.getAliases().stream().anyMatch(a -> a.equalsIgnoreCase(name))) {
                return cmd;
            }
        }
        return null;
    }

    /* =====================
       Configuration
       ===================== */

    public CommandHandler permissionMessage(String msg) {
        this.permissionMessage = msg;
        return this;
    }

    public CommandHandler playerOnlyMessage(String msg) {
        this.playerOnlyMessage = msg;
        return this;
    }

    public CommandHandler consoleOnlyMessage(String msg) {
        this.consoleOnlyMessage = msg;
        return this;
    }

    /* =====================
       Internals
       ===================== */

    private CommandMap resolveCommandMap() {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getServer());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to access CommandMap", e);
        }
    }

    private String color(String msg) {
        return ChatColor.translate('&', msg);
    }
}
