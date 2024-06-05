package fr.deroffal.eshop.price.domain.cucumber.steps;

import static org.mockito.Mockito.doReturn;

import fr.deroffal.eshop.price.domain.DiscountPort;
import java.math.BigDecimal;
import java.util.Optional;

import fr.deroffal.eshop.price.domain.model.Discount;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscountStepDefinition {

    @Autowired
    private DiscountPort discountPort;

    @Given("a discount of {bigdecimal} % from {int} {string}")
    public void registerDiscount(BigDecimal amount, int threshold, String name) {
        final Discount discount = new Discount(name, threshold, amount);
        doReturn(Optional.of(discount)).when(discountPort).loadByItemName(name);
    }

}
