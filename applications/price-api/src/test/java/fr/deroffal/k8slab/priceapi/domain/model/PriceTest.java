package fr.deroffal.k8slab.priceapi.domain.model;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.deroffal.k8slab.priceapi.utils.PriceMatcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PriceTest {

  @Test
  void euros_returns_priceWithEurCurrency() {
    Price price = Price.euros(2311);
    assertThat(price).satisfies(PriceMatcher.of(2311, "EUR"));
  }

  @Test
  void usd_returns_priceWithUSCurrency() {
    Price price = Price.usd(2311);
    assertThat(price).satisfies(PriceMatcher.of(2311, "USD"));
  }

  @Test
  void zeroEuro_returns_0Euro() {
    assertThat(Price.ZERO_EURO).satisfies(PriceMatcher.of(ZERO, "EUR"));
  }

  @Nested
  @DisplayName("add")
  class add {

    @Test
    @DisplayName("throws NPE when other is null")
    void add_throwsNpe_whenOtherIsNull() {
      var price = Price.euros(1);

      assertThatThrownBy(() -> price.add(null))
          .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("throws exception when other currency does not match")
    void add_throwsException_whenCurrencyDoesNotMatch() {
      var price = Price.euros(1);

      assertThatThrownBy(() -> price.add(new Price(ZERO, "USD")))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Currencies do not match.");
    }
  }

  @Test
  @DisplayName("add amount when currencies match")
  void add_addsAmount_whenCurrenciesMatch() {
    var price = Price.euros(150);
    var other = Price.euros(810);

    var sum = price.add(other);

    assertThat(sum).satisfies(PriceMatcher.of(960, "EUR"));
  }


}
