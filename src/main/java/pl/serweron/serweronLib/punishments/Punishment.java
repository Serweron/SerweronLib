package pl.serweron.serweronLib.punishments;

import java.util.UUID;

/**
 * Represents a single punishment entry stored in the system.
 */
public class Punishment {
    private final UUID id;
    private final UUID targetUuid;
    private final UUID staffUuid;
    private final PunishmentType type;
    private final String reason;
    private final long issuedAt;
    private final Long expiresAt;
    private final PunishmentScope scope;

    public Punishment(UUID id, UUID targetUuid, UUID staffUuid, PunishmentType type, String reason,
                      long issuedAt, Long expiresAt, PunishmentScope scope) {
        this.id = id;
        this.targetUuid = targetUuid;
        this.staffUuid = staffUuid;
        this.type = type;
        this.reason = reason;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.scope = scope;
    }

    public UUID getId() { return id; }
    public UUID getTargetUuid() { return targetUuid; }
    public UUID getStaffUuid() { return staffUuid; }
    public PunishmentType getType() { return type; }
    public String getReason() { return reason; }
    public long getIssuedAt() { return issuedAt; }
    public Long getExpiresAt() { return expiresAt; }
    public PunishmentScope getScope() { return scope; }
}
