package fr.deroffal.k8slab.priceapi.domain.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import fr.deroffal.k8slab.priceapi.domain.CartCalculationRequest;
import fr.deroffal.k8slab.priceapi.domain.PriceCalculator;
import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

public class CartStepDefinition {

  @Autowired
  private PriceCalculator priceCalculator;

  private final CartCalculationRequest cart = new CartCalculationRequest(new ArrayList<>());
  private double actualPrice;

  @Given("I add {int} {string} in my cart")
  public void addItemToCart(final long quantity, final String item) {
    cart.items().add(new CartItem(item, quantity));
  }

  @When("I validate my cart")
  public void validateCart() {
    actualPrice = priceCalculator.getPrice(cart);
  }

  @Then("I should pay {double}")
  public void iShouldPay(final double expectedPrice) {
    assertThat(actualPrice).isEqualTo(expectedPrice);
  }
}
