package fr.deroffal.eshop.price.domain.cucumber.steps;

import fr.deroffal.eshop.price.domain.PriceCalculationRequest;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.Price;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.UUID;

public class CartStepDefinition {

    @Autowired
    private PriceCalculator priceCalculator;

    @Autowired
    private PriceCalculationContext priceCalculationContext;

    private final PriceCalculationRequest cart = new PriceCalculationRequest(new ArrayList<>());
    private Mono<Price> actualPrice;

    @Given("I add {int} {string} in my cart")
    public void addItemToCart(final long quantity, final String name) {
        UUID uuid = priceCalculationContext.getItemByName(name);
        cart.items().add(new CartItem(uuid, quantity));
    }

    @Given("I add the item {string} in my cart")
    public void addItemToCart(final String name) {
        addItemToCart(1, name);
    }

    @When("I validate my cart")
    public void validateCart() {
        actualPrice = priceCalculator.getPrice(cart);
    }

    @Then("I should pay {price}")
    public void iShouldPay(final Price expectedPrice) {
        StepVerifier.create(actualPrice)
                .expectNext(expectedPrice)
                .verifyComplete();
    }

}
