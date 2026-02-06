package pl.serweron.serweronLib.command.context.completions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.serweron.serweronLib.command.context.CommandContext;

import java.util.List;

public class OnlinePlayersCompletion implements CompletionProvider {
    @Override
    public List<String> complete(CommandContext ctx) {
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }
}
