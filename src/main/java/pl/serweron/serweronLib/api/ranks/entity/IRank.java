package pl.serweron.serweronLib.api.ranks.entity;

import pl.serweron.serweronLib.ranks.MetaData;
import pl.serweron.serweronLib.ranks.Permission;
import pl.serweron.serweronLib.utils.Response;

/**
 * Represents a single rank definition in the system.
 * A rank consists of its name, metadata, weight (priority), and assigned permissions.
 * <p>
 * Supports both permanent and time-limited permissions.
 */
public interface IRank {

    /**
     * Gets the unique name of the rank (e.g., "admin", "vip").
     *
     * @return the rank's name
     */
    String getName();

    /**
     * Gets the metadata associated with the rank.
     * Metadata may include prefix, suffix, chat color, etc.
     *
     * @return metadata object
     */
    MetaData getMetadata();

    /**
     * Gets the weight (priority) of the rank.
     * Higher weight means higher precedence in comparisons.
     *
     * @return numeric weight value
     */
    int getWeight();

    /**
     * Sets the weight (priority) of the rank.
     *
     * @param weight the new weight
     * @return weight
     */
    Response<Integer> setWeight(int weight);

    // ─────────────────────────────────────────────────────────────
    // ░░░░░░░░░░ PERMISSIONS ░░░░░░░░░░
    // ─────────────────────────────────────────────────────────────

    /**
     * Adds a permanent permission to the rank.
     *
     * @param permission the permission node (e.g., "essentials.fly")
     * @return Response indicating success or failure
     */
    Response<String> addPermission(Permission permission);

    /**
     * Removes a permanent permission from the rank.
     *
     * @param permission the permission node
     * @return Response indicating success or failure
     */
    Response<String> removePermission(String permission);

    /**
     * Checks if the rank has the specified permission.
     *
     * @param permission the permission node to check
     * @return true if found, false otherwise
     */
    boolean hasPermission(String permission);
}
