package pl.serweron.serweronLib.api.ranks.managers;

import pl.serweron.serweronLib.api.ranks.entity.IRankPlayer;
import pl.serweron.serweronLib.api.ranks.entity.IRank;
import pl.serweron.serweronLib.utils.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for managing rank-related operations for players and global rank handling.
 * Provides methods to query rank data, permissions, prefixes/suffixes, and manage the lifecycle of ranks.
 */
public interface IRankManager {

    // -------------------- Player-related methods --------------------

    /**
     * Retrieves the {@link IRankPlayer} instance associated with a given player UUID.
     *
     * @param uuid the UUID of the player
     * @return the IRankPlayer representation for the player
     */
    Optional<IRankPlayer> getRankPlayer(UUID uuid);

    /**
     * Gets the player's highest rank based on weight or priority.
     *
     * @param uuid the UUID of the player
     * @return the highest {@link IRank} of the player, or null if none assigned
     */
    Response<IRank> getPlayerHighestRank(UUID uuid);

    /**
     * Checks whether the player has a rank with the specified name.
     *
     * @param uuid     the UUID of the player
     * @param rankName the name of the rank to check
     * @return true if the player has the rank, false otherwise
     */
    Response<Boolean> playerHasRank(UUID uuid, String rankName);

    /**
     * Checks if the player has a specific permission.
     * <p>
     * This check should respect all assigned ranks and their permissions.
     *
     * @param uuid       the UUID of the player
     * @param permission the permission node to check
     * @return true if the player has the permission, false otherwise
     */
    Response<Boolean> playerHasPermission(UUID uuid, String permission);

    /**
     * Retrieves the highest-priority prefix for a player.
     *
     * @param uuid the UUID of the player
     * @return the prefix string, or null if none found
     */
    Response<String> getPlayerPrefix(UUID uuid);

    /**
     * Retrieves the highest-priority suffix for a player.
     *
     * @param uuid the UUID of the player
     * @return the suffix string, or null if none found
     */
    Response<String> getPlayerSuffix(UUID uuid);

    // -------------------- Rank management methods --------------------

    /**
     * Creates and registers a new rank in the system.
     *
     * @param rank the rank to create
     * @return created rank
     */
    Response<IRank> createRank(IRank rank);

    /**
     * Deletes a rank from the system.
     *
     * @param rank the rank to delete
     * @return whether the operation has been performed
     */
    Response<Boolean> deleteRank(IRank rank);

    /**
     * Retrieves a rank by its name.
     *
     * @param name the name of the rank
     * @return the corresponding {@link IRank}, or null if not found
     */
    Optional<IRank> getRank(String name);
}
