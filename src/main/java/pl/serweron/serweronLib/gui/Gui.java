package pl.serweron.serweronLib.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an abstract GUI framework for managing custom inventories.
 * Subclasses must implement {@link #init()} to define their layout and behavior.
 * This class handles GUI state, click events, and visual elements.
 */
public abstract class Gui implements Listener {

    /** Bukkit inventory representing the GUI. */
    protected final Inventory inventory;

    /** Map of slots to their associated elements. */
    protected final Map<GuiSlot, GuiElement> elements = new HashMap<>();

    /** Whether clicks in the GUI should be cancelled (non-interactive with Bukkit). */
    protected final boolean interactive;

    /**
     * Constructs a new GUI and registers event listeners.
     *
     * @param plugin       The plugin instance.
     * @param size         The size of the inventory (must be a multiple of 9).
     * @param title        The title displayed at the top of the inventory.
     * @param interactive  Whether to cancel default interaction behavior.
     */
    public Gui(JavaPlugin plugin, int size, String title, boolean interactive) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.inventory = Bukkit.createInventory(null, size, Component.text(title));
        this.interactive = interactive;
        init();
    }

    /**
     * Called once on GUI creation to initialize contents.
     * Should be overridden by subclasses to set up the layout and elements.
     */
    protected abstract void init();

    /**
     * Opens the GUI for a given player.
     *
     * @param player The player who should see the inventory.
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Sets a GUI element at the specified slot.
     *
     * @param guiElement The element to place into the inventory.
     */
    public void setGuiElement(GuiElement guiElement) {
        inventory.setItem(guiElement.getSlot().toSlotIndex(), guiElement.getItem());
        elements.put(guiElement.getSlot(), guiElement);
    }

    /**
     * Clears all items and elements from the GUI.
     */
    public void clear() {
        inventory.clear();
        elements.clear();
    }

    /**
     * Removes an element from a specific slot in the GUI.
     *
     * @param slot The slot from which to remove the element.
     */
    public void removeElement(GuiSlot slot) {
        inventory.clear(slot.toSlotIndex());
        elements.remove(slot);
    }

    /**
     * Fills the outer border of the GUI with the provided border element.
     *
     * @param borderElement The element to be placed in border slots.
     */
    public void fillBorders(GuiElement borderElement) {
        for (int x = 0; x < 9; x++) {
            setGuiElement(new GuiElement(new GuiSlot(x, 0), borderElement.getItem(), borderElement.getOnClick()));
            setGuiElement(new GuiElement(new GuiSlot(x, 5), borderElement.getItem(), borderElement.getOnClick()));
        }
        for (int y = 1; y < 5; y++) {
            setGuiElement(new GuiElement(new GuiSlot(0, y), borderElement.getItem(), borderElement.getOnClick()));
            setGuiElement(new GuiElement(new GuiSlot(8, y), borderElement.getItem(), borderElement.getOnClick()));
        }
    }

    /**
     * Fills all empty (null) slots in the inventory with the specified background item.
     *
     * @param backgroundItem ItemStack to be used as the background filler.
     */
    public void background(ItemStack backgroundItem) {
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if (inventory.getItem(slot) == null) {
                GuiSlot guiSlot = GuiSlot.fromSlotIndex(slot);
                GuiElement filler = new GuiElement(guiSlot, backgroundItem, (player, gui) -> {});
                setGuiElement(filler);
            }
        }
    }

    /**
     * Handles inventory click events and dispatches them to the associated GUI elements.
     * Cancels the event if the GUI is marked as interactive.
     *
     * @param event The click event triggered by the player.
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(inventory)) return;

        GuiElement element = elements.get(GuiSlot.fromSlotIndex(event.getSlot()));
        if (element == null) return;

        GuiClickHandler handler = element.getOnClick();
        if (handler == null) return;

        handler.handleClick(event, this);

        if (interactive) event.setCancelled(true);
    }
}
