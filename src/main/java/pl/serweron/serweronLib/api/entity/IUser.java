package pl.serweron.serweronLib.api.entity;

import java.util.UUID;

/**
 * Represents a User in game,
 * Including uuid and name
 */
public interface IUser {
    /**
     * Gets a user UUID
     *
     * @return User UUID
     */
    UUID getUUID();

    /**
     * Gets a username
     *
     * @return User name
     */
    String getName();
}
