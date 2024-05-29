package fr.deroffal.k8slab.priceapi.utils;

import static org.assertj.core.api.Assertions.assertThat;

import fr.deroffal.k8slab.priceapi.domain.model.Price;
import java.math.BigDecimal;
import org.assertj.core.api.ThrowingConsumer;

public class PriceMatcher implements ThrowingConsumer<Price> {

  private final BigDecimal expectedAmount;
  private final String expectedCurrency;

  private PriceMatcher(BigDecimal expectedAmount, String expectedCurrency) {
    this.expectedAmount = expectedAmount;
    this.expectedCurrency = expectedCurrency;
  }

  private PriceMatcher(int expectedAmount, String expectedCurrency) {
    this.expectedAmount = new BigDecimal(expectedAmount);
    this.expectedCurrency = expectedCurrency;
  }

  public static PriceMatcher of(BigDecimal expectedAmount, String expectedCurrency) {
    return new PriceMatcher(expectedAmount, expectedCurrency);
  }

  public static PriceMatcher of(int expectedAmount, String expectedCurrency) {
    return new PriceMatcher(expectedAmount, expectedCurrency);
  }

  @Override
  public void acceptThrows(Price input) {
    assertThat(input.amount()).isEqualTo(expectedAmount);
    assertThat(input.currency()).isEqualTo(expectedCurrency);
  }
}
