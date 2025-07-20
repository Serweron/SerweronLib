package pl.serweron.serweronLib.gui;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * A builder class for constructing GUI instances using a fluent API.
 * Allows adding elements, setting background items, and filling borders.
 */
@RequiredArgsConstructor
public class GuiBuilder {

    private final JavaPlugin plugin;
    private final int size;
    private final String title;
    private final boolean interactive;

    private final Map<GuiSlot, GuiElement> elements = new HashMap<>();
    private ItemStack backgroundItem = null;

    /**
     * Adds a GUI element to the builder.
     *
     * @param guiElement the element to be added
     * @return the current builder instance
     */
    public GuiBuilder element(GuiElement guiElement) {
        elements.put(guiElement.getSlot(), guiElement);
        return this;
    }

    /**
     * Adds a GUI element at a specific slot with an item and click handler.
     *
     * @param slot    the GUI slot
     * @param item    the item to display
     * @param handler the click handler
     * @return the current builder instance
     */
    public GuiBuilder element(GuiSlot slot, ItemStack item, GuiClickHandler handler) {
        return element(new GuiElement(slot, item, handler));
    }

    /**
     * Adds a GUI element at a specific slot with an item and no interaction logic.
     *
     * @param slot the GUI slot
     * @param item the item to display
     * @return the current builder instance
     */
    public GuiBuilder element(GuiSlot slot, ItemStack item) {
        return element(new GuiElement(slot, item, (player, gui) -> {}));
    }

    /**
     * Fills the border slots (top row, bottom row, left and right edges)
     * with the specified border element, unless already filled.
     *
     * @param borderElement the element to use for the borders
     * @return the current builder instance
     */
    public GuiBuilder fillBorders(GuiElement borderElement) {
        for (int y = 0; y < size / 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (y == 0 || y == (size / 9) - 1 || x == 0 || x == 8) {
                    GuiSlot slot = new GuiSlot(x, y);
                    if (!elements.containsKey(slot)) {
                        elements.put(slot, new GuiElement(slot, borderElement.getItem(), borderElement.getOnClick()));
                    }
                }
            }
        }
        return this;
    }

    /**
     * Sets the background item for all empty slots in the GUI.
     *
     * @param backgroundItem the item to use as the background
     * @return the current builder instance
     */
    public GuiBuilder backgroundItem(ItemStack backgroundItem) {
        this.backgroundItem = backgroundItem;
        return this;
    }

    /**
     * Builds the GUI instance using the provided configuration.
     *
     * @return a new {@link Gui} instance
     */
    public Gui build() {
        return new GuiInstance(plugin, size, title, interactive);
    }

    /**
     * Builds the GUI and immediately opens it for the specified player.
     *
     * @param player the player to open the GUI for
     */
    public void open(Player player) {
        build().open(player);
    }

    /**
     * Internal class that represents the actual GUI instance built by this builder.
     */
    private class GuiInstance extends Gui {

        public GuiInstance(JavaPlugin plugin, int size, String title, boolean interactive) {
            super(plugin, size, title, interactive);
        }

        /**
         * Initializes the GUI with configured elements and optional background.
         */
        @Override
        protected void init() {
            for (GuiElement element : elements.values()) {
                setGuiElement(element);
            }

            if (backgroundItem != null) {
                background(backgroundItem);
            }
        }
    }
}
