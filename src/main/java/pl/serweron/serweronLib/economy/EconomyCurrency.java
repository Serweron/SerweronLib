package pl.serweron.serweronLib.economy;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the currency used in the economy system.
 * Contains basic properties such as name, symbol, and format pattern.
 */
@AllArgsConstructor
public class EconomyCurrency {

    /**
     * The full name of the currency (e.g., "Dollar", "Euro").
     */
    @Getter
    private String name;

    /**
     * The symbol representing the currency (e.g., "$", "€").
     */
    @Getter
    private String symbol;

    /**
     * The formatting string or pattern used to display amounts (e.g., "#,##0.00 $").
     */
    @Getter
    private String format;
}
