package fr.deroffal.k8slab.priceapi.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Price(BigDecimal amount, String currency) {

  public static Price euros(String amount) {
    return new Price(new BigDecimal(amount), "EUR");
  }

  public Price sum(Price other) {
    Objects.requireNonNull(other);
    if (!this.currency.equals(other.currency())) {
      throw new IllegalArgumentException("Currencies do not match.");
    }
    return new Price(amount.add(other.amount), currency);
  }
}
