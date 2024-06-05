package fr.deroffal.eshop.price.domain.model;

import static java.math.BigDecimal.ZERO;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import fr.deroffal.eshop.price.domain.model.Discount;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountTest {

  @ParameterizedTest
  @CsvSource({
      "book, 4 , true",
      "book, 5 , true",
      "book, 6 , false",
      "ball, 10, false"
  })
  void isRelevantOn(final String discountItemName, final int discountItemThreshold, final boolean expectedRelevance) {
    //FIXME
//    //given:
//    final CartItem cartItem = new CartItem("book", 5);
//
//    //when:
//    final Discount discount = new Discount(discountItemName, discountItemThreshold, ZERO);
//
//    //then:
//    assertThat(discount.isRelevantOn(cartItem)).isEqualTo(expectedRelevance);
  }

  @ParameterizedTest
  @CsvSource({
      "10000, 10.00, 9000",
      "2700, 50.00, 1350",
      "2311, 30.00, 1617"
  })
  void applyTo(final BigDecimal price, final BigDecimal discountAmount, final BigDecimal expectedValue) {
    //given:
    final Discount discount = new Discount("book", 1, discountAmount);

    //when:
    final BigDecimal actualValue = discount.applyTo(price);

    //then:
    assertThat(actualValue).isEqualTo(expectedValue);
  }
}
