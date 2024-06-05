package fr.deroffal.eshop.price.domain.cucumber.steps;

import fr.deroffal.eshop.price.domain.PriceCalculationRequest;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.Price;
import fr.deroffal.eshop.price.utils.PriceMatcher;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
