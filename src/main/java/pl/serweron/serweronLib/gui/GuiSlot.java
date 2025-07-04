package pl.serweron.serweronLib.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GuiSlot {
    private int x;
    private int y;

    public int toSlotIndex() {
        return y * 9 + x;
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