package pl.serweron.serweronLib.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LazyLocation {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private String worldName;

    public boolean isWorldNull() {
        return worldName == null;
    }

    public void teleportPlayer(Player player) {
        player.teleport(toLocation());
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
    }
    public static LazyLocation fromLocation(Location loc) {
        return new LazyLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch(), loc.getWorld().getName());
    }
}