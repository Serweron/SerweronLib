package pl.serweron.serweronLib.api.entity;

import java.util.List;

/**
 * Represents a rank in Rank System, including its name, prefix, suffix, weight, and permissions.
 */
public interface IRank {

    /**
     * Gets the name of the rank.
     *
     * @return the name of the rank
     */
    String getName();

    /**
     * Gets the prefix of the rank (e.g., displayed before a player's name).
     *
     * @return the rank prefix
     */
    String getPrefix();

    /**
     * Sets the prefix of the rank with a specified display weight.
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
     * Gets the weight of the rank.
     * Higher weight usually means higher priority or importance.
     *
     * @return the weight of the rank
     */
    int getWeight();

    /**
     * Sets the weight of the rank.
     * Higher weight usually means higher priority or importance.
     *
     * @param weight the new weight of the rank
     */
    void setWeight(int weight);

    /**
     * Adds a permission to the rank.
     *
     * @param permission the permission to add
     */
    void addPermission(String permission);

    /**
     * Removes a permission from the rank.
     *
     * @param permission the permission to remove
     */
    void removePermission(String permission);

    /**
     * Checks if the rank has a specific permission.
     *
     * @param permission the permission to check
     * @return {@code true} if the rank has the permission, {@code false} otherwise
     */
    boolean hasPermission(String permission);

    /**
     * Gets the list of permissions assigned to the rank.
     *
     * @return a list of permissions
     */
    List<String> getPermissions();
}
