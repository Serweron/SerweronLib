package pl.serweron.serweronLib.items;

import net.kyori.adventure.text.Component;
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

/**
 * Utility class for building and customizing {@link ItemStack} instances in a fluent and readable manner.
 * <p>
 * Supports setting display name, lore, and custom {@link org.bukkit.persistence.PersistentDataContainer} values.
 */
public class ItemBuilder {

    /** The item being built. */
    private final ItemStack item;

    /** The meta data associated with the item. */
    private final ItemMeta meta;

    /**
     * Creates a new {@code ItemBuilder} instance using the given material.
     *
     * @param material the {@link Material} type for the item
     */
    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();
    }

    /**
     * Sets the display name of the item using color codes (prefixed with '&amp;').
     *
     * @param name the display name with optional color codes
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder name(String name) {
        meta.displayName(Component.text(ChatColor.translate('&', name)));
        return this;
    }

    /**
     * Sets the lore (description) of the item.
     *
     * @param lines an array of strings, each representing a lore line (supports '&amp;' color codes)
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder lore(String... lines) {
        List<Component> lore = Arrays.stream(lines)
                .map(s -> Component.text(ChatColor.translate('&', s)))
                .collect(Collectors.toList());
        meta.lore(lore);
        return this;
    }

    /**
     * Adds a string value to the item's {@link org.bukkit.persistence.PersistentDataContainer} using the specified key.
     *
     * @param key    the key used to store the value
     * @param value  the string value to store
     * @param plugin the owning plugin (used to namespace the key)
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder setPDC(String key, String value, JavaPlugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
        return this;
    }

    /**
     * Retrieves a string value from the item's {@link org.bukkit.persistence.PersistentDataContainer} using the specified key.
     *
     * @param key    the key associated with the stored value
     * @param plugin the owning plugin (used to namespace the key)
     * @return the stored string value, or {@code null} if not present
     */
    public String getPDC(String key, JavaPlugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return meta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    /**
     * Finalizes and returns the built {@link ItemStack} with all modifications applied.
     *
     * @return the constructed {@code ItemStack}
     */
    public ItemStack build() {
        item.setItemMeta(meta);
        return item;
    }
}
