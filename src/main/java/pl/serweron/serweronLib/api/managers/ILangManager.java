package pl.serweron.serweronLib.api.managers;

/**
 * Interface responsible for managing language messages (localization) in the plugin.
 * <p>
 * It allows retrieval of translated or predefined messages by key,
 * supports fallback values, and provides a reload mechanism for dynamic language file updates.
 */
public interface ILangManager {

    /**
     * Returns the currently active language name (e.g. "pl", "en", "de").
     *
     * @return name of the current language
     */
    String getLangName();

    /**
     * Retrieves a translated message based on the provided key.
     * <p>
     * If the key is not found, a placeholder like {@code %key%} may be returned depending on the implementation.
     *
     * @param key the message key to look up
     * @return the translated message or a fallback placeholder
     */
    String getMessage(String key);

    /**
     * Retrieves a translated message based on the provided key,
     * or returns the specified default value if the key is not found.
     *
     * @param key          the message key to look up
     * @param defaultValue the value to return if the key is missing
     * @return the translated message or the default value
     */
    String getMessage(String key, String defaultValue);

    /**
     * Reloads the language configuration (e.g., from a YAML or JSON file).
     * <p>
     * Should be called after modifying language files to apply changes at runtime.
     */
    void reload();
}
