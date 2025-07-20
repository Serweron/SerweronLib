import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.jupiter.api.Test;
import pl.serweron.serweronLib.utils.LazyLocation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LazyLocationTest {

    @Test
    void testFromAndToLocation() {
        World world = mock(World.class);
        when(world.getName()).thenReturn("world");

        Location loc = new Location(world, 1, 2, 3, 90, 45);
        LazyLocation lazy = LazyLocation.fromLocation(loc);

        assertEquals(1, lazy.getX());
        assertEquals("world", lazy.getWorldName());

        // Mock Bukkit.getWorld
        mockStatic(Bukkit.class).when(() -> Bukkit.getWorld("world")).thenReturn(world);
        Location loc2 = lazy.toLocation();
        assertEquals(1, loc2.getX());
    }

    @Test
    void testWorldNull() {
        LazyLocation loc = new LazyLocation(0, 0, 0, 0, 0, null);
        assertTrue(loc.isWorldNull());
    }
}
