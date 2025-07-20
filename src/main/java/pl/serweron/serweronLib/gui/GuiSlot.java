package pl.serweron.serweronLib.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a 2D coordinate (x, y) in a standard Bukkit GUI grid.
 * Each GUI has 9 columns per row, and typically up to 6 rows (total 54 slots).
 */
@Getter
@AllArgsConstructor
public class GuiSlot {
    private int x;
    private int y;

    /**
     * Converts this 2D coordinate (x, y) into a flat slot index (0–53).
     *
     * @return the corresponding slot index
     */
    public int toSlotIndex() {
        return y * 9 + x;
    }

    /**
     * Converts a flat slot index (0–53) into a {@link GuiSlot} with (x, y) coordinates.
     *
     * @param slotIndex the flat inventory slot index
     * @return the corresponding {@link GuiSlot}
     * @throws IllegalArgumentException if index is outside 0–53
     */
    public static GuiSlot fromSlotIndex(int slotIndex) {
        if (slotIndex < 0 || slotIndex >= 54)
            throw new IllegalArgumentException("Slot index must be in range 0-53");

        int x = slotIndex % 9;
        int y = slotIndex / 9;
        return new GuiSlot(x, y);
    }

    @Override
    public String toString() {
        return "GuiSlot{x=" + x + ", y=" + y + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GuiSlot)) return false;
        GuiSlot that = (GuiSlot) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    /**
     * A static array of all possible slots (0–53) mapped to their (x, y) positions.
     * Useful for iterating or referencing precomputed coordinates.
     */
    public static final GuiSlot[] GUI_SLOTS = new GuiSlot[54];

    static {
        int index = 0;
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 9; x++) {
                GUI_SLOTS[index++] = new GuiSlot(x, y);
            }
        }
    }
}
