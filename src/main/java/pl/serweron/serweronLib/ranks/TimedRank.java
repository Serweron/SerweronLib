package pl.serweron.serweronLib.ranks;

import java.time.Instant;

/**
 * Represents a time-limited rank assigned to a player.
 * Useful for temporary VIP access or timed promotions.
 */
public class TimedRank {

    /** The name of the rank (e.g. "vip", "admin") */
    private final String rankName;

    /** The timestamp when the rank was assigned */
    private final Instant assignedAt;

    /** The timestamp when the rank will expire (null = permanent) */
    private final Instant expiresAt;

    /**
     * Constructs a new TimedRank.
     *
     * @param rankName   the name of the rank
     * @param assignedAt the timestamp when the rank was assigned
     * @param expiresAt  the timestamp when the rank expires (null if permanent)
     */
    public TimedRank(String rankName, Instant assignedAt, Instant expiresAt) {
        this.rankName = rankName;
        this.assignedAt = assignedAt;
        this.expiresAt = expiresAt;
    }

    /**
     * Gets the rank name.
     *
     * @return the name of the rank
     */
    public String getRankName() {
        return rankName;
    }

    /**
     * Gets the timestamp when the rank was assigned.
     *
     * @return assignment time
     */
    public Instant getAssignedAt() {
        return assignedAt;
    }

    /**
     * Gets the timestamp when the rank will expire.
     *
     * @return expiration time, or null if permanent
     */
    public Instant getExpiresAt() {
        return expiresAt;
    }

    /**
     * Checks if the rank has expired.
     *
     * @return true if the rank is expired, false otherwise
     */
    public boolean isExpired() {
        return expiresAt != null && Instant.now().isAfter(expiresAt);
    }
}
