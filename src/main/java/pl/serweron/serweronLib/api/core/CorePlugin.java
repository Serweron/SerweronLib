package pl.serweron.serweronLib.api.core;

import org.bukkit.plugin.Plugin;
import pl.serweron.serweronLib.api.databases.Database;
import pl.serweron.serweronLib.api.managers.IRankManager;
import pl.serweron.serweronLib.commands.CommandHandler;
import pl.serweron.serweronLib.langs.LangManager;

public interface CorePlugin extends Plugin {
    // Database
    Database getDatabase();

    // Managers
    LangManager getLangManager();
    IRankManager getRankManager();

    // CommandHandler
    CommandHandler getCommandHandler();
}
