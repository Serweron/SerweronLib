package pl.serweron.serweronLib.command.context.completions;

import pl.serweron.serweronLib.command.context.CommandContext;

import java.util.List;

public interface CompletionProvider {
    List<String> complete(CommandContext ctx);
}
