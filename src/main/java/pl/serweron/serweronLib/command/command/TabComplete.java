package pl.serweron.serweronLib.command.command;


import org.bukkit.command.CommandSender;

import java.util.List;

@FunctionalInterface
public interface TabComplete {

    List<String> handle(CommandSender sender, BaseCommand command, String label, String[] args);
}
