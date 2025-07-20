package pl.serweron.serweronLib.api.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a User in game,
 * Including uuid and name
 */
public interface IUser {
    /**
     * Gets a user UUID
     *
     * @return User UUID
     */
    UUID getUUID();

    /**
     * Gets a username
     *
     * @return User name
     */
    String getName();

    /**
     * Check player is online
     *
     * @return whether the player is online
     */
    default boolean isOnline() {
        return Bukkit.getPlayer(getUUID()) != null;
    }

    /**
     * Convert to Server Player
     *
     * @return Server Player
     */
    default Optional<Player> toPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(getUUID()));
    }
}
