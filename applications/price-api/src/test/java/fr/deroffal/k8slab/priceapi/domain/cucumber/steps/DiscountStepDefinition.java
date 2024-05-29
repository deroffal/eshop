package fr.deroffal.k8slab.priceapi.domain.cucumber.steps;

import static org.mockito.Mockito.doReturn;

import fr.deroffal.k8slab.priceapi.domain.DiscountPort;
import fr.deroffal.k8slab.priceapi.domain.model.Discount;
import java.util.Optional;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class DiscountStepDefinition {

    @Autowired
    private DiscountPort discountPort;

    @Given("a discount of {int}% from {int} {string}")
    public void registerDiscount(int amount, int threshold, String name) {
        final Discount discount = new Discount(name, threshold, amount);
        doReturn(Optional.of(discount)).when(discountPort).loadByItemName(name);
    }
}
