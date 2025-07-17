package pl.serweron.serweronLib.api.ranks.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.serweron.serweronLib.api.entity.IUser;
import pl.serweron.serweronLib.ranks.MetaData;
import pl.serweron.serweronLib.utils.Response;

import java.time.Duration;

/**
 * Represents a player in the Rank System.
 * A rank player can hold multiple ranks (permanent or temporary) and permissions.
 * Inherits identity from {@link IUser}.
 */
public interface IRankPlayer extends IUser {

    /**
     * Gets the player's metadata (prefix, suffix, chat color, etc.).
     *
     * @return metadata object
     */
    MetaData getMetadata();

    // ─────────────────────────────────────────────────────────────
    // ░░░░░░░░░░ PERMISSIONS ░░░░░░░░░░
    // ─────────────────────────────────────────────────────────────

    /**
     * Adds a permanent permission to the player.
     *
     * @param permission permission node
     * @return Response indicating success or error
     */
    Response<String> addPermission(String permission);

    /**
     * Removes permission from the player.
     *
     * @param permission permission node
     * @return Response
     */
    Response<String> removePermission(String permission);

    /**
     * Adds a temporary permission to the player for a specified duration.
     *
     * @param permission permission node
     * @param duration   how long the permission should last
     * @return Response
     */
    Response<String> addTemporaryPermission(String permission, Duration duration);

    /**
     * Checks if the player has a permission.
     *
     * @param permission permission node
     * @return true if player has the permission, false otherwise
     */
    boolean hasPermission(String permission);

    // ─────────────────────────────────────────────────────────────
    // ░░░░░░░░░░ RANKS ░░░░░░░░░░
    // ─────────────────────────────────────────────────────────────

    /**
     * Sets a permanent main rank for the player.
     *
     * @param rank rank name
     * @return Response
     */
    Response<String> setRank(String rank);

    /**
     * Adds a permanent rank to the player.
     *
     * @param rank rank name
     * @return Response
     */
    Response<String> addRank(String rank);

    /**
     * Removes rank from the player.
     *
     * @param rank rank name
     * @return Response
     */
    Response<String> removeRank(String rank);

    /**
     * Adds a temporary rank to the player for a specified duration.
     *
     * @param rank     rank name
     * @param duration how long the rank should last
     * @return Response
     */
    Response<String> addTemporaryRank(String rank, Duration duration);

    /**
     * Checks if the player has a specific rank.
     *
     * @param rank rank name
     * @return true if assigned
     */
    boolean hasRank(String rank);

    // ─────────────────────────────────────────────────────────────
    // ░░░░░░░░░░ UTIL ░░░░░░░░░░
    // ─────────────────────────────────────────────────────────────

    /**
     * Converts IRankPlayer to a live Bukkit Player instance.
     * Returns null if player is offline.
     *
     * @return Bukkit Player or null
     */
    default Player getPlayer() {
        return Bukkit.getPlayer(getUUID());
    }
}
