package pl.serweron.serweronLib.economy;

import pl.serweron.serweronLib.utils.Response;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Represents the outcome of a balance-changing transaction in the economy system.
 * Extends {@link Response} to provide status information along with transaction details.
 * Contains the previous balance, the amount changed, and the resulting new balance.
 */
@Getter
public class TransactionResult extends Response<BigDecimal> {

    /**
     * The amount by which the balance was changed.
     * Positive values indicate deposits, negative values indicate withdrawals.
     */
    private final BigDecimal amountChanged;

    /**
     * The balance before the transaction was applied.
     */
    private final BigDecimal oldBalance;

    /**
     * The balance after the transaction was applied.
     */
    private final BigDecimal newBalance;

    private TransactionResult(ResponseType type, String errorMessage, BigDecimal newBalance,
                              BigDecimal amountChanged, BigDecimal oldBalance) {
        super(type, errorMessage, newBalance);
        this.amountChanged = amountChanged;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }

    /**
     * Creates a successful transaction result.
     *
     * @param oldBalance    the balance before the transaction
     * @param amountChanged the amount added to or withdrawn from the balance
     * @return a successful {@link TransactionResult} instance with updated balances
     */
    public static TransactionResult success(BigDecimal oldBalance, BigDecimal amountChanged) {
        BigDecimal newBalance = oldBalance.add(amountChanged);
        return new TransactionResult(ResponseType.SUCCESS, null, newBalance, amountChanged, oldBalance);
    }

    /**
     * Creates a failed transaction result with an error message.
     *
     * @param message          the failure reason or error message
     * @param oldBalance       the balance before the transaction attempt
     * @param attemptedChange  the amount that was attempted to be changed
     * @return a failed {@link TransactionResult} instance preserving the old balance
     */
    public static TransactionResult failure(String message, BigDecimal oldBalance, BigDecimal attemptedChange) {
        return new TransactionResult(ResponseType.FAILURE, message, oldBalance, attemptedChange, oldBalance);
    }

    /**
     * Creates a transaction result indicating the operation is not implemented.
     *
     * @return a {@link TransactionResult} with NOT_IMPLEMENTED status
     */
    public static TransactionResult notImplementedTransaction() {
        return new TransactionResult(ResponseType.NOT_IMPLEMENTED, "Transaction not implemented", null, null, null);
    }

    /**
     * Checks if this transaction was a deposit.
     *
     * @return true if the amount changed is positive, false otherwise
     */
    public boolean isDeposit() {
        return amountChanged != null && amountChanged.signum() > 0;
    }

    /**
     * Checks if this transaction was a withdrawal.
     *
     * @return true if the amount changed is negative, false otherwise
     */
    public boolean isWithdrawal() {
        return amountChanged != null && amountChanged.signum() < 0;
    }
}
