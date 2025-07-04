package pl.serweron.serweronLib.gui;


import org.bukkit.entity.Player;

public abstract class SimpleGui extends Gui {
    public SimpleGui(Player player, int size, String title) {
        super(player, size, title);
    }


    public void render(Player player, int size, String title) {

    }
}
