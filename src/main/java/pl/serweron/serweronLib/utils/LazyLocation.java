package pl.serweron.serweronLib.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * A lightweight representation of a {@link Location} in a Minecraft world.
 * Stores basic spatial coordinates (x, y, z), orientation (yaw, pitch), and the name of the world.
 * Useful for serializing and deserializing player locations without retaining full Location objects.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LazyLocation {

    /** The X coordinate of the location. */
    private double x;

    /** The Y coordinate of the location. */
    private double y;

    /** The Z coordinate of the location. */
    private double z;

    /** The yaw (horizontal rotation) of the location. */
    private float yaw;

    /** The pitch (vertical rotation) of the location. */
    private float pitch;

    /** The name of the world where the location resides. */
    private String worldName;

    /**
     * Checks whether the world name is null.
     *
     * @return {@code true} if worldName is null, otherwise {@code false}
     */
    public boolean isWorldNull() {
        return worldName == null;
    }

    /**
     * Teleports the given player to this LazyLocation.
     *
     * @param player the player to teleport
     */
    public void teleportPlayer(Player player) {
        player.teleport(toLocation());
    }

    /**
     * Converts this LazyLocation into a Bukkit {@link Location} object.
     *
     * @return a new Location instance with the stored coordinates and orientation
     */
    public Location toLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }

    /**
     * Creates a LazyLocation from a Bukkit {@link Location}.
     *
     * @param loc the original Bukkit Location
     * @return a new LazyLocation instance containing the same data
     */
    public static LazyLocation fromLocation(Location loc) {
        return new LazyLocation(
                loc.getX(),
                loc.getY(),
                loc.getZ(),
                loc.getYaw(),
                loc.getPitch(),
                loc.getWorld().getName()
        );
    }
}
