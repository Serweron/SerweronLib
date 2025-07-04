package pl.serweron.serweronLib.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import pl.serweron.serweronLib.colors.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    public ItemBuilder name(String name) {
        meta.setDisplayName(ChatColor.translate('&', name));
        return this;
    }

    public ItemBuilder lore(String... lines) {
        List<String> lore = Arrays.stream(lines)
                .map(s -> ChatColor.translate('&', s))
                .collect(Collectors.toList());
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder setPDC(String key, String value, JavaPlugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
        return this;
    }

    public String getPDC(String key, JavaPlugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return meta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
