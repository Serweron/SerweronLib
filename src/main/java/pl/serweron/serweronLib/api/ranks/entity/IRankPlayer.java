package pl.serweron.serweronLib.api.ranks.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.serweron.serweronLib.api.entity.IUser;
import pl.serweron.serweronLib.ranks.MetaData;
import pl.serweron.serweronLib.utils.Response;

import java.util.List;

/**
 * Represents a Player in Rank System
 * Including its UUID, name, metada, weight, permissions, ranks
 */
public interface IRankPlayer extends IUser {

    /**
     * Gets Metadata of the player
     *
     * @return the player metadata
     */
    MetaData getMetadata();

    /**
     * Adds a permission to the player.
     *
     * @param permission the permission to add
     * @return Response
     */
    Response<String> addPermission(String permission);

    /**
     * Removes a permission from the player.
     *
     * @param permission the permission to remove
     * @return Response
     */
    Response<String> removePermission(String permission);

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
     * @return Response
     */
    Response<String> setRank(String rank);

    /**
     * Add a rank to the player
     *
     * @param rank the rank add
     * @return Response
     */
    Response<String> addRank(String rank);

    /**
     * Removes a rank from the player
     *
     * @param rank the rank remove
     * @return Response
     */
    Response<String> removeRank(String rank);

    /**
     * Checks if the player has a specific rank.
     *
     * @param rank the rank to check
     * @return {@code true} if the player has rank , {@code false} otherwise
     */
    boolean hasRank(String rank);

    /**
     * Gets a ranks list
     *
     * @return Ranks list
    */
    List<String> getAllRanks();

    /**
     * Convert IRankPlayer to {@link Player}
     *
     * @param rankPlayer Rank player
     * @return Return a server player
     */
    static Player toPlayer(IRankPlayer rankPlayer) {
        return Bukkit.getPlayer(rankPlayer.getUUID());
    }
}