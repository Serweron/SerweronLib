package pl.serweron.serweronLib.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Gui {

    protected final Player player;
    protected final Inventory inventory;

    public Gui(Player player, int size, String title) {
        this.player = player;
        this.inventory = Bukkit.createInventory(null, size, title);
        init();
    }

    protected abstract void init();

    public void open() {
        player.openInventory(inventory);
    }

    public void setItem(int x, int y, ItemStack item) {
        inventory.setItem(y * 9 + x, item);
    }

    public abstract void handleClick(InventoryClickEvent event);
}
