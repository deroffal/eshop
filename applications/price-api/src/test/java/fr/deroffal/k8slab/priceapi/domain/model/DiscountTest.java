package fr.deroffal.k8slab.priceapi.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

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
    void isRelevantOn(final String discountItemName, final int discountItemThreshold, final boolean expectedRelevance){
        //given:
        final CartItem cartItem = new CartItem("book", 5);

        //when:
        final Discount discount = new Discount(discountItemName, discountItemThreshold, 0);

        //then:
        assertThat(discount.isRelevantOn(cartItem)).isEqualTo(expectedRelevance);
    }

    @ParameterizedTest
    @CsvSource({
        "100.00, 10.00 ,90.00",
        " 27.00, 50.00 ,13.50"
    })
    void applyTo(final double price, final double amount, final double expectedValue){
        //given:
        final Discount discount = new Discount("book", 1, amount);

        //when:
        final double actualValue = discount.applyTo(price);

        //then:
        assertThat(actualValue).isEqualTo(expectedValue);
    }
}
