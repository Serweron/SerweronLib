package pl.serweron.serweronLib.gui;

import org.bukkit.entity.Player;

public abstract class PagedGui extends Gui {
    public PagedGui(Player player, int size, String title) {
        super(player, size, title);
    }
}
