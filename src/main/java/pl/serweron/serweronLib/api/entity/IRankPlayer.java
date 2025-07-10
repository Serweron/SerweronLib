package pl.serweron.serweronLib.api.entity;

import java.util.List;
import java.util.UUID;

/**
 * Represents a Player in Rank System
 * Including its UUID, name, prefix, suffix, weight, permissions, ranks
 */
public interface IRankPlayer extends IUser {

    /**
     * Gets the prefix of the player
     *
     * @return the rank prefix
     */
    String getPrefix();

    /**
     * Sets the prefix of the player with a specified display weight.
     *
     * @param prefix the new prefix
     * @param weight the weight to determine display priority
     */
    void setPrefix(String prefix, int weight);

    /**
     * Gets the suffix of the rank (e.g., displayed after a player's name).
     *
     * @return the rank suffix
     */
    String getSuffix();

    /**
     * Sets the suffix of the rank with a specified display weight.
     *
     * @param suffix the new suffix
     * @param weight the weight to determine display priority
     */
    void setSuffix(String suffix, int weight);

    /**
     * Adds a permission to the player.
     *
     * @param permission the permission to add
     */
    void addPermission(String permission);

    /**
     * Removes a permission from the player.
     *
     * @param permission the permission to remove
     */
    void removePermission(String permission);

    /**
     * Checks if the player has a specific permission.
     *
     * @param permission the permission to check
     * @return {@code true} if the rank has the permission, {@code false} otherwise
     */
    boolean hasPermission(String permission);

    /**
     * Gets the list of permissions assigned to the player.
     *
     * @return a list of permissions
     */
    List<String> getPermissions();

    // Player ranks

    /**
     * Sets the rank of the player
     *
     * @param rank the new rank
     */
    void setRank(String rank);

    /**
     * Add a rank to the player
     *
     * @param rank the rank add
     */
    void addRank(String rank);

    /**
     * Removes a rank from the player
     *
     * @param rank the rank remove
     */
    void removeRank(String rank);

    /**
     * Checks if the player has a specific rank.
     *
     * @param rank the rank to check
     * @return {@code true} if the player has rank , {@code false} otherwise
     */
    boolean hasRank(String rank);
    List<String> getAllRanks();
}