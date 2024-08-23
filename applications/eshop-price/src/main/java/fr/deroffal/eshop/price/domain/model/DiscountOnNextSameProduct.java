package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import static java.math.BigDecimal.ZERO;

public record DiscountOnNextSameProduct(UUID product, int threshold, BigDecimal discountPercentage) {

    public boolean isApplicableOn(UUID productCandidate, long quantity) {
        return productCandidate.equals(this.product()) && quantity > this.threshold();
    }

    public Price applyDiscount(Price unitPrice, long quantity) {
        int count = 0;
        boolean nextToAdjust = false;

        Price price = new Price(ZERO, unitPrice.currency());

        for (long i = quantity; i > 0; i--) {
            if (nextToAdjust) {
                Price adjustedPrice = unitPrice.percentageOf(discountPercentage);
                price = price.add(adjustedPrice);
                nextToAdjust = false;
            } else {
                count += 1;
                price = price.add(unitPrice);
            }
            if (count == this.threshold) {
                nextToAdjust = true;
                count = 0;
            }
        }
        return price;
    }
}
