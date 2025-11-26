package pl.serweron.serweronLib.api.punishments;

import pl.serweron.serweronLib.punishments.Punishment;
import pl.serweron.serweronLib.punishments.PunishmentType;

import java.util.List;
import java.util.UUID;

/**
 * Unified manager for all punishment operations.
 * <p>
 * Centralizes logic for bans, mutes, warns, kicks, and IP-based actions.
 * Supports filtering, history lookup, and active state checks.
 */
public interface IPunishmentManager {

    // === BANS ===

    /**
     * Apply a permanent ban to a user.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID issuing the ban.
     * @param reason Reason for the ban.
     * @return Created PunishmentRecord representing the ban.
     */
    Punishment banUser(UUID player, UUID staff, String reason);

    /**
     * Apply a temporary ban to a user.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID issuing the ban.
     * @param reason Reason for the ban.
     * @param durationMillis Duration of the ban in milliseconds.
     * @return Created PunishmentRecord representing the temporary ban.
     */
    Punishment tempBanUser(UUID player, UUID staff, String reason, long durationMillis);

    /**
     * Remove an active ban from a user.
     *
     * @param player Target player UUID.
     * @return true if the ban was successfully removed, false otherwise.
     */
    boolean unbanUser(UUID player);

    /**
     * Check if a user is currently banned.
     *
     * @param player Target player UUID.
     * @return true if the player is banned, false otherwise.
     */
    boolean isBanned(UUID player);

    /**
     * Apply an IP-based ban.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID issuing the IP ban.
     * @param reason Reason for the IP ban.
     * @return Created PunishmentRecord representing the IP ban.
     */
    Punishment ipBan(UUID player, UUID staff, String reason);

    /**
     * Apply an IP-based ban.
     *
     * @param ip Target IP address.
     * @param staff Staff member UUID issuing the IP ban.
     * @param reason Reason for the IP ban.
     * @return Created PunishmentRecord representing the IP ban.
     */
    Punishment ipBan(String ip, UUID staff, String reason);

    /**
     * Remove an active IP-based ban.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID removing the IP ban.
     * @return true if the IP ban was successfully removed, false otherwise.
     */
    boolean unIpBan(UUID player, UUID staff);

    /**
     * Remove an active IP-based ban.
     *
     * @param ip Target IP address.
     * @param staff Staff member UUID removing the IP ban.
     * @return true if the IP ban was successfully removed, false otherwise.
     */
    boolean unIpBan(String ip, UUID staff);


    // === MUTES ===

    /**
     * Apply a permanent mute to a user.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID issuing the mute.
     * @param reason Reason for the mute.
     * @return Created PunishmentRecord representing the mute.
     */
    Punishment muteUser(UUID player, UUID staff, String reason);

    /**
     * Apply a temporary mute to a user.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID issuing the mute.
     * @param reason Reason for the mute.
     * @param durationMillis Duration of the mute in milliseconds.
     * @return Created PunishmentRecord representing the temporary mute.
     */
    Punishment tempMuteUser(UUID player, UUID staff, String reason, long durationMillis);

    /**
     * Remove an active mute from a user.
     *
     * @param player Target player UUID.
     * @return true if the mute was successfully removed, false otherwise.
     */
    boolean unmuteUser(UUID player);

    /**
     * Check if a user is currently muted.
     *
     * @param player Target player UUID.
     * @return true if the player is muted, false otherwise.
     */
    boolean isMuted(UUID player);


    // === WARNINGS ===

    /**
     * Issue a warning to a user.
     *
     * @param player Target player UUID.
     * @param staff Staff member UUID issuing the warning.
     * @param reason Reason for the warning.
     * @return Created PunishmentRecord representing the warning.
     */
    Punishment warnUser(UUID player, UUID staff, String reason);

    /**
     * Remove a warning from a user.
     *
     * @param player Target player UUID.
     * @return true if the warning was successfully removed, false otherwise.
     */
    boolean unwarnUser(UUID player);

    /**
     * Check if a user has any active warnings.
     *
     * @param player Target player UUID.
     * @return true if the player has active warnings, false otherwise.
     */
    boolean isWarned(UUID player);


    // === HISTORY ===

    /**
     * Returns the complete punishment history of a player, including bans, mutes, warns, and kicks.
     *
     * @param player Target player UUID.
     * @return List of PunishmentRecord objects for the player.
     */
    List<Punishment> getUserHistory(UUID player);

    /**
     * Returns filtered punishment history of a player.
     *
     * @param player Target player UUID.
     * @param types List of PunishmentType to include (empty = all types).
     * @param activeOnly If true, includes only active punishments.
     * @return List of PunishmentRecord objects matching the filter criteria.
     */
    List<Punishment> getUserHistory(UUID player, List<PunishmentType> types, boolean activeOnly);

    /**
     * Returns all punishments issued by a specific staff member.
     *
     * @param staff Staff member UUID.
     * @return List of PunishmentRecord objects issued by the staff member.
     */
    List<Punishment> getStaffHistory(UUID staff);


    // === ALT DETECTION ===

    /**
     * Get all player UUIDs associated with a specific IP.
     *
     * @param ip IP address.
     * @return List of UUIDs associated with the IP.
     */
    List<UUID> getAccountsByIp(String ip);

    /**
     * Get all known IP addresses associated with a player.
     *
     * @param player Target player UUID.
     * @return List of IP addresses linked to the player.
     */
    List<String> getKnownIps(UUID player);
}
