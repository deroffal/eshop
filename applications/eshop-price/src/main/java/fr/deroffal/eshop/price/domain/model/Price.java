package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

/**
 * A price
 *
 * @param amount   express as minor currency unit (10.00 €-> 1000 / 1000¥ -> 1000)
 * @param currency currency code. See {@link Currency#getCurrencyCode()}
 */
public record Price(BigDecimal amount, String currency) {

    public static Price ZERO_EURO = euros(0);

    public static Price euros(int amount) {
        return new Price(new BigDecimal(amount), "EUR");
    }

    public static Price usd(int amount) {
        return new Price(new BigDecimal(amount), "USD");
    }

    public Price add(Price other) {
        Objects.requireNonNull(other);
        if (!this.currency.equals(other.currency())) {
            throw new IllegalArgumentException("Currencies do not match.");
        }
        return new Price(amount.add(other.amount), currency);
    }
}
