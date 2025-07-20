package pl.serweron.serweronLib.api.economy.entity;

import pl.serweron.serweronLib.api.entity.IUser;
import pl.serweron.serweronLib.economy.TransactionResult;
import pl.serweron.serweronLib.utils.Response;

import java.math.BigDecimal;

/**
 * Represents an economy account tied to a user within the economy system.
 * Provides methods to access balance, perform transactions, and manage the account lifecycle.
 * Extends {@link IUser} to inherit user identification and metadata.
 */
public interface IEconomyAccount extends IUser {
    /**
     * Returns the current balance of the account.
     *
     * @return the balance as a {@link BigDecimal}
     */
    BigDecimal getBalance();

    /**
     * Attempts to withdraw a specified amount from the account balance.
     *
     * @param amount the amount to withdraw (using double for convenience, but BigDecimal recommended internally)
     * @return a {@link TransactionResult} indicating success, failure, or errors of the operation
     */
    TransactionResult withdraw(double amount);

    /**
     * Attempts to deposit a specified amount into the account balance.
     *
     * @param amount the amount to deposit
     * @return a {@link TransactionResult} indicating success, failure, or errors of the operation
     */
    TransactionResult deposit(double amount);

    /**
     * Checks if the account currently has at least the specified amount.
     *
     * @param amount the amount to check
     * @return true if the balance is greater than or equal to the amount, false otherwise
     */
    boolean has(BigDecimal amount);

    /**
     * Deletes the economy account and performs necessary cleanup.
     *
     * @return a {@link Response} containing the deleted {@link IEconomyAccount} or error details
     */
    Response<IEconomyAccount> delete();
}
