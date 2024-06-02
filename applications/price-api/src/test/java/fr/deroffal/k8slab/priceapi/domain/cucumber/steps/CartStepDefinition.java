package fr.deroffal.k8slab.priceapi.domain.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import fr.deroffal.k8slab.priceapi.domain.PriceCalculationRequest;
import fr.deroffal.k8slab.priceapi.domain.PriceCalculator;
import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import fr.deroffal.k8slab.priceapi.domain.model.Price;
import fr.deroffal.k8slab.priceapi.utils.PriceMatcher;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class CartStepDefinition {

  @Autowired
  private PriceCalculator priceCalculator;

  @Autowired
  private StepContext stepContext ;

  private final PriceCalculationRequest cart = new PriceCalculationRequest(new ArrayList<>());
  private Price actualPrice;

  @Given("I add {int} {string} in my cart")
  public void addItemToCart(final long quantity, final String item) {
    UUID uuid = stepContext.getItemByName(item);
    cart.items().add(new CartItem(uuid, quantity));
  }

  @When("I validate my cart")
  public void validateCart() {
    actualPrice = priceCalculator.getPrice(cart);
  }

  @Then("I should pay {bigdecimal} {string}")
  public void iShouldPay(final BigDecimal expectedPrice, final String expectedCurrency) {
    assertThat(actualPrice).satisfies(PriceMatcher.of(expectedPrice, expectedCurrency));
  }

}
