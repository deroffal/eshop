package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import static java.math.RoundingMode.DOWN;

/**
 * A price.
 *
 * @param amount   express as minor currency unit (10.00 €-> 1000 / 1000¥ -> 1000)
 * @param currency currency code. See {@link Currency#getCurrencyCode()}
 */
public record Price(BigDecimal amount, String currency) {

    public static Price ZERO_EURO = euros(0);
    public static final BigDecimal HUNDRED = new BigDecimal(100);

    public static Price euros(int amount) {
        return new Price(new BigDecimal(amount), "EUR");
    }

    public static Price usd(int amount) {
        return new Price(new BigDecimal(amount), "USD");
    }

    /**
     * Add to an existing price the amount of another price.
     * @param other another {@link Price}.
     * @return a new instance of {@link Price}, with the sum of the two amounts.
     * @throws NullPointerException if other is null, {@link IllegalArgumentException} if currencies do not match.
     */
    public Price add(Price other) {
        Objects.requireNonNull(other);
        if (!this.currency.equals(other.currency())) {
            throw new IllegalArgumentException("Currencies do not match.");
        }
        return new Price(amount.add(other.amount), currency);
    }

    /**
     * Multiply the amount of a price.
     * @param time the number of time to multiply
     * @return a new instance of {@link Price}, with the multiplication of the two amounts.
     */
    public Price times(long time){
        return new Price(amount.multiply(BigDecimal.valueOf(time)), currency);
    }

    public Price percentageOf(BigDecimal discountAmount){
        BigDecimal percentage = HUNDRED.add(discountAmount.negate()).movePointLeft(2);
        return new Price(amount.multiply(percentage).setScale(0, DOWN), currency);
    }
}
