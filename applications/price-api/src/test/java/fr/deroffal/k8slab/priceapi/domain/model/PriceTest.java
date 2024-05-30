package fr.deroffal.k8slab.priceapi.domain.model;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

class PriceTest {

  @Test
  void euros_returns_priceWithEurCurrency() {
    Price price = Price.euros("23.11");
    assertThat(price.amount()).isEqualTo(BigDecimal.valueOf(23.11));
    assertThat(price.currency()).isEqualTo("EUR");
  }

  @Nested
  @DisplayName("sum")
  class Sum {

    @Test
    @DisplayName("throws NPE when other is null")
    void sum_throwsNpe_whenOtherIsNull() {
      var price = Price.euros("1");

      assertThatThrownBy(() -> price.sum(null))
          .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("throws exception when other currency does not match")
    void sum_throwsException_whenCurrencyDoesNotMatch() {
      var price = Price.euros("1");

      assertThatThrownBy(() -> price.sum(new Price(ZERO, "USD")))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Currencies do not match.");
    }
  }

  @Test
  @DisplayName("add amount when currencies match")
  void addAmount_addsAmount_whenCurrenciesMatch() {
    var price = Price.euros("1.50");
    var other = Price.euros("8.10");

    var sum = price.sum(other);

    assertThat(sum.currency()).isEqualTo("EUR");
    assertThat(sum.amount()).isEqualTo(new BigDecimal("9.60"));
  }


}
