package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record DiscountOnProduct(UUID product, BigDecimal discountPercentage) {

    public Price applyOn(Price price) {
        return price.percentageOf(discountPercentage);
    }

}
