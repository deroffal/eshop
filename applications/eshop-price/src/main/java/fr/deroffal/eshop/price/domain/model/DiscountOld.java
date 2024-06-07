package fr.deroffal.eshop.price.domain.model;

import static java.math.RoundingMode.DOWN;

import java.math.BigDecimal;

public record DiscountOld(String itemName, int threshold, BigDecimal amount) {

  public static final BigDecimal HUNDRED = new BigDecimal(100);

  public boolean isRelevantOn(final CartItem cartItem) {
    return cartItem.product().equals(itemName) && cartItem.quantity() >= threshold;
  }

  public BigDecimal applyTo(final BigDecimal price) {
    return computeDiscount(price, amount);
  }

  public static BigDecimal computeDiscount(BigDecimal originalPrice, BigDecimal discountAmount) {
    BigDecimal percentage = HUNDRED.add(discountAmount.negate()).movePointLeft(2);
    return originalPrice.multiply(percentage).setScale(0, DOWN);
  }
}
