package pl.serweron.serweronLib.api.core;

import org.bukkit.plugin.Plugin;
import pl.serweron.serweronLib.api.databases.Database;
import pl.serweron.serweronLib.api.managers.IRankManager;
import pl.serweron.serweronLib.commands.CommandHandler;
import pl.serweron.serweronLib.langs.SingleLangManager;

/**
 * Represents the main contract for a core plugin that extends the Bukkit {@link Plugin} interface.
 * This interface defines access points to the key components required by the plugin's infrastructure,
 * such as database handling, command registration, language management, and rank management.
 */
public interface CorePlugin extends Plugin {

    /**
     * Gets the database handler used by the plugin.
     * <p>
     * This allows access to persistent storage and is typically implemented via a JDBC-based backend.
     *
     * @return the {@link Database} instance
     */
    Database getDatabase();

    /**
     * Gets the language manager responsible for localization and message retrieval.
     *
     * @return the {@link SingleLangManager} instance
     */
    SingleLangManager getLangManager();

    /**
     * Gets the rank manager used to handle player ranks and permissions.
     *
     * @return the {@link IRankManager} instance
     */
    IRankManager getRankManager();

    /**
     * Gets the command handler used for registering and managing plugin commands.
     *
     * @return the {@link CommandHandler} instance
     */
    CommandHandler getCommandHandler();
}
