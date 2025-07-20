package pl.serweron.serweronLib.api.economy.managers;

import pl.serweron.serweronLib.api.economy.entity.IEconomyAccount;
import pl.serweron.serweronLib.economy.EconomyCurrency;
import pl.serweron.serweronLib.utils.Response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for managing economy accounts and handling basic economic operations.
 * Designed to support a single-currency system, where each player has exactly one account.
 */
public interface IEconomyManager {

    /**
     * Retrieves the currency used in this economy system.
     *
     * @return the {@link EconomyCurrency} instance representing the currency
     */
    EconomyCurrency getCurrency();

    /**
     * Fetches the economy account for a given player, if it exists.
     *
     * @param playerUUID the UUID of the player
     * @return an {@link Optional} containing the account if present, otherwise empty
     */
    Optional<IEconomyAccount> getAccount(UUID playerUUID);

    /**
     * Creates a new economy account for the given player.
     * If an account already exists, the operation may return an error response.
     *
     * @param playerUUID the UUID of the player
     * @return a {@link Response} containing the created {@link IEconomyAccount} or error information
     */
    Response<IEconomyAccount> createAccount(UUID playerUUID, String name);

    /**
     * Deletes the economy account associated with the given player.
     *
     * @param playerUUID the UUID of the player
     * @return a {@link Response} containing the deleted {@link IEconomyAccount} or error information
     */
    Response<IEconomyAccount> deleteAccount(UUID playerUUID);

    /**
     * Checks whether an economy account exists for the specified player.
     *
     * @param playerUUID the UUID of the player
     * @return a {@link Response} containing a Boolean value: true if the account exists, false otherwise
     */
    Response<Boolean> accountExists(UUID playerUUID);

    /**
     * Retrieves a list of all UUIDs that currently have an economy account.
     *
     * @return a list of player UUIDs with active accounts
     */
    List<UUID> getAccounts();
}
