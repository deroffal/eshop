package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.RoundingMode.DOWN;

public record DiscountOnItem(UUID product, BigDecimal discountPercentage) {

    public static final BigDecimal HUNDRED = new BigDecimal(100);

    public Price applyOn(Price price) {
        BigDecimal amount = computeDiscount(price.amount(), discountPercentage);
        return new Price(amount, price.currency());
    }

    private static BigDecimal computeDiscount(BigDecimal originalPrice, BigDecimal discountAmount) {
        BigDecimal percentage = HUNDRED.add(discountAmount.negate()).movePointLeft(2);
        return originalPrice.multiply(percentage).setScale(0, DOWN);
    }

}
