package pl.serweron.serweronLib.api.ranks.entity;

import pl.serweron.serweronLib.ranks.MetaData;
import pl.serweron.serweronLib.utils.Response;

import java.util.List;

/**
 * Represents a rank in Rank System, including its name, metada, weight, and permissions.
 */
public interface IRank {

    /**
     * Gets the name of the rank.
     *
     * @return the name of the rank
     */
    String getName();

    /**
     * Gets Metadata of the ranks
     *
     * @return the rank metada
     */
    MetaData getMetadata();

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
     * @return Response
     */
    Response<String> setWeight(int weight);

    /**
     * Adds a permission to the rank.
     *
     * @param permission the permission to add
     * @return Response
     */
    Response<String> addPermission(String permission);

    /**
     * Removes a permission from the rank.
     *
     * @param permission the permission to remove
     * @return Response
     */
    Response<String> removePermission(String permission);

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
