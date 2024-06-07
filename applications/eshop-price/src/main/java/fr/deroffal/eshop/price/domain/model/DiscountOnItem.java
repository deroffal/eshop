package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record DiscountOnItem(UUID product, BigDecimal discountPercentage) {
    public Price applyOn(Price price) {
        BigDecimal amount = DiscountOld.computeDiscount(price.amount(), discountPercentage);
        return new Price(amount, price.currency());
    }
}
