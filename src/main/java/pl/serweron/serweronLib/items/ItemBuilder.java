package pl.serweron.serweronLib.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
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
    private ItemStack item;

    /** The meta data associated with the item. */
    private ItemMeta meta;

    /**
     * Creates a new {@code ItemBuilder} instance with a default material of {@link Material#AIR}.
     */
    public ItemBuilder() {
        this.item = new ItemStack(Material.AIR);
        this.meta = item.getItemMeta();
    }

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
     * Creates a new {@code ItemBuilder} instance using the provided {@link ItemStack}.
     *
     * @param item the existing {@code ItemStack} to modify
     */
    public ItemBuilder(ItemStack item) {
        this.item = item;
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
     * Clears a specific line from the item's lore.
     *
     * @param line the lore line to remove (supports '&amp;' color codes)
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder clearLore(String line) {
        Component lineComponent = Component.text(ChatColor.translate('&', line));
        List<Component> lore = meta.lore();
        if (lore != null && lore.contains(lineComponent)) {
            meta.lore(lore);
        }
        return this;
    }

    /**
     * Clears all lore from the item.
     *
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder clearLore() {
        meta.lore(null);
        return this;
    }

    /**
     * Sets the material type of the item.
     *
     * @param material the {@link Material} type to set
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder type(Material material) {
        this.item = item.withType(material);
        return this;
    }

    /**
     * Sets the amount of items in the stack.
     *
     * @param amount the quantity to set
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder amount(int amount) {
        this.item.setAmount(amount);
        return this;
    }

    /**
     * Sets the durability (damage) of the item.
     *
     * @param durability the durability value to set
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder durability(int durability) {
        if (meta instanceof Damageable damageable) {
            damageable.setDamage(durability);
            meta = damageable;
        }
        return this;
    }

    /**
     * Sets whether the item is unbreakable.
     *
     * @param unbreakable {@code true} to make the item unbreakable, {@code false} otherwise
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder unbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Adds an enchantment to the item.
     *
     * @param enchantment the {@link Enchantment} to add
     * @param level       the level of the enchantment
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder enchantment(Enchantment enchantment, int level) {
        meta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Adds an enchantment to the item with a default level of 1.
     *
     * @param enchantment the {@link Enchantment} to add
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder enchantment(Enchantment enchantment) {
        meta.addEnchant(enchantment, 1, true);
        return this;
    }

    /**
     * Removes a specific enchantment from the item.
     *
     * @param enchantment the {@link Enchantment} to remove
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        meta.removeEnchant(enchantment);
        return this;
    }

    /**
     * Clears all enchantments from the item.
     *
     * @return the current {@code ItemBuilder} instance for chaining
     */
    public ItemBuilder clearEnchantments() {
        meta.getEnchants().keySet().forEach(meta::removeEnchant);
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
    public ItemBuilder setPDCKey(String key, String value, JavaPlugin plugin) {
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
    public String getPDCKey(String key, JavaPlugin plugin) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        return meta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    public ItemMeta meta() {
        return meta;
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

