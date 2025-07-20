package pl.serweron.serweronLib.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Functional interface used to handle clicks on {@link GuiElement}s inside a custom {@link Gui}.
 * <p>
 * Implementations define how a click event should be processed for the specific element.
 * </p>
 *
 * Example usage with a lambda:
 * <pre>{@code
 * new GuiElement(slot, itemStack, (event, gui) -> {
 *     Player player = (Player) event.getWhoClicked();
 *     player.sendMessage("You clicked on a GUI item!");
 * });
 * }</pre>
 */
@FunctionalInterface
public interface GuiClickHandler {

    /**
     * Handles a click on a GUI element.
     *
     * @param event the original {@link InventoryClickEvent} fired by Bukkit
     * @param gui   the GUI instance where the event occurred
     */
    void handleClick(InventoryClickEvent event, Gui gui);
}
