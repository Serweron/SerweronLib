package pl.serweron.serweronLib.gui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

/**
 * Represents a single element inside a {@link Gui}.
 * <p>
 * Each element has a position represented by {@link GuiSlot}, an item to display,
 * and an optional click handler for interaction.
 * </p>
 */
@AllArgsConstructor
public class GuiElement {

    /**
     * The position of this element in the GUI grid.
     */
    @Getter
    private GuiSlot slot;

    /**
     * The {@link ItemStack} displayed in this element's slot.
     */
    @Getter
    private ItemStack item;

    /**
     * The click handler executed when this element is clicked.
     * May be {@code null} if the element is not interactive.
     */
    @Getter
    private GuiClickHandler onClick;

    /**
     * Constructs a non-interactive GUI element with no click handler.
     *
     * @param slot the slot position
     * @param item the item to display
     */
    public GuiElement(GuiSlot slot, ItemStack item) {
        this.slot = slot;
        this.item = item;
        this.onClick = null;
    }
}
