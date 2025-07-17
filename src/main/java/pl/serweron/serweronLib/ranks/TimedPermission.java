package pl.serweron.serweronLib.ranks;

import java.time.Instant;

/**
 * Represents a time-limited permission assigned to a player.
 * Useful for temporary abilities (e.g. fly for 24h).
 */
public class TimedPermission {

    /** The permission node (e.g. "essentials.fly") */
    private final String permission;

    /** The timestamp when the permission was granted */
    private final Instant grantedAt;

    /** The timestamp when the permission will expire (null = permanent) */
    private final Instant expiresAt;

    /**
     * Constructs a new TimedPermission.
     *
     * @param permission the permission node
     * @param grantedAt  the timestamp when the permission was granted
     * @param expiresAt  the expiration time (null if permanent)
     */
    public TimedPermission(String permission, Instant grantedAt, Instant expiresAt) {
        this.permission = permission;
        this.grantedAt = grantedAt;
        this.expiresAt = expiresAt;
    }

    /**
     * Gets the permission node.
     *
     * @return the permission node string
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Gets the timestamp when the permission was granted.
     *
     * @return grant time
     */
    public Instant getGrantedAt() {
        return grantedAt;
    }

    /**
     * Gets the expiration time.
     *
     * @return expiration time, or null if permanent
     */
    public Instant getExpiresAt() {
        return expiresAt;
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
