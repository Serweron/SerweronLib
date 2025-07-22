package pl.serweron.serweronLib.ranks;

import lombok.Getter;

import java.time.Instant;

/**
 * Represents a time-limited permission assigned to a player.
 * Useful for temporary abilities (e.g. fly for 24h).
 */
public class Permission {

    /** The permission node (e.g. "essentials.fly") */
    @Getter
    private final String permission;

    /** The timestamp when the permission will expire (null = permanent) */
    @Getter
    private final Instant expiresAt;

    /** Whether the permission is enabled or not */
    @Getter
    private final boolean enable;

    /**
     * Constructs a new TimedPermission.
     *
     * @param permission the permission node
     * @param enable whether the permission is enabled or not
     */
    public Permission(String permission, boolean enable) {
        this.permission = permission;
        this.expiresAt = null;
        this.enable = enable;
    }

    /**
     * Constructs a new TimedPermission.
     *
     * @param permission the permission node
     * @param enable whether the permission is enabled or not
     * @param expiresAt  the expiration time (null if permanent)
     */
    public Permission(String permission, boolean enable, Instant expiresAt) {
        this.permission = permission;
        this.expiresAt = expiresAt;
        this.enable = enable;
    }

    /**
     * Checks if the permission is expired.
     *
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        return expiresAt != null && Instant.now().isAfter(expiresAt);
    }
}
