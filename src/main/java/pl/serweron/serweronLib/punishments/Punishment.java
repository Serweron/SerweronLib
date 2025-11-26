package pl.serweron.serweronLib.punishments;

import lombok.Getter;

import java.util.UUID;

/**
 * Represents a single punishment entry stored in the system.
 */
@Getter
public class Punishment {
    private final UUID id;
    private final UUID targetUuid;
    private final UUID staffUuid;
    private final PunishmentType type;
    private final String reason;
    private final long issuedAt;
    private final Long expiresAt;

    public Punishment(UUID id, UUID targetUuid, UUID staffUuid, PunishmentType type, String reason,
                      long issuedAt, Long expiresAt) {
        this.id = id;
        this.targetUuid = targetUuid;
        this.staffUuid = staffUuid;
        this.type = type;
        this.reason = reason;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
    }

}
