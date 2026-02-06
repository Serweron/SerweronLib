package pl.serweron.serweronLib.command;

import org.jetbrains.annotations.NotNull;
import pl.serweron.serweronLib.SerweronLib;
import pl.serweron.serweronLib.command.annotations.Aliases;
import pl.serweron.serweronLib.command.annotations.Description;
import pl.serweron.serweronLib.command.annotations.Permission;
import pl.serweron.serweronLib.command.command.BaseCommand;
import pl.serweron.serweronLib.command.context.CommandContext;
import pl.serweron.serweronLib.registry.GlobalRegistry;

import java.util.List;

@Aliases("lib")
@Description("Serweron lib command")
@Permission("serweronlib.admin")
public class LibCommand extends BaseCommand {

    public LibCommand(SerweronLib serweronLib) {
        super("serweronlib", serweronLib);
    }

    @Override
    protected void execute(@NotNull CommandContext ctx) {
        ctx.getSender().sendMessage(String.format("[%s] SerweronLib v%s Information", ctx.getPlugin().getPluginMeta().getName(), ctx.getPlugin().getPluginMeta().getVersion()));
        ctx.getSender().sendMessage(String.format("[%s] Economy: %s", ctx.getPlugin().getPluginMeta().getName(), GlobalRegistry.getEconomyManagerPluginName()));
        ctx.getSender().sendMessage(String.format("[%s] Ranks: %s", ctx.getPlugin().getPluginMeta().getName(), GlobalRegistry.getRankMangerPluginName()));
    }
}
