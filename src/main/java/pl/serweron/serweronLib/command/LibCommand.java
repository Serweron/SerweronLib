package pl.serweron.serweronLib.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pl.serweron.serweronLib.SerweronLib;
import pl.serweron.serweronLib.command.annotations.*;
import pl.serweron.serweronLib.registry.EconomyAPI;
import pl.serweron.serweronLib.registry.RankAPI;

import java.util.List;

@NotFromConsole
@DefaultNumberOfArgs(number = 1)
@CommandData(aliases = {"lib"}, description = "Serweron lib command", permission = "serweronlib.admin")
public class LibCommand extends SCommand {
    private SerweronLib serweronLib;

    public LibCommand(SerweronLib serweronLib) {
        super("serweronlib");
        this.serweronLib = serweronLib;
    }
    @Override
    public void execute(@NotNull CommandSender sender, String[] args) {
        sender.sendMessage(String.format("[%s] SerweronLib v%s Information", serweronLib.getPluginMeta().getName(), serweronLib.getPluginMeta().getVersion()));
        sender.sendMessage(String.format("[%s] Economy: %s", serweronLib.getPluginMeta().getName(), EconomyAPI.getPluginName()));
        sender.sendMessage(String.format("[%s] Ranks: %s", serweronLib.getPluginMeta().getName(), RankAPI.getPluginName()));
    }

    @Override
    public @NotNull List<String> onTabComplete(@NotNull CommandSender sender, String[] args) {
        return List.of();
    }
}
