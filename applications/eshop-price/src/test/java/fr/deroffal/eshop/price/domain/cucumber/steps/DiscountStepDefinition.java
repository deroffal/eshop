package fr.deroffal.eshop.price.domain.cucumber.steps;

import fr.deroffal.eshop.price.domain.DiscountPort;
import fr.deroffal.eshop.price.domain.model.DiscountOnProduct;
import fr.deroffal.eshop.price.domain.model.DiscountOnNextSameProduct;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static fr.deroffal.eshop.price.domain.model.Price.HUNDRED;
import static org.mockito.Mockito.when;

public class DiscountStepDefinition {

    @Autowired
    private DiscountPort discountPort;

    @Autowired
    private PriceCalculationContext priceCalculationContext;

    @Before
    public void before() {
        when(discountPort.loadDiscountOnType()).thenReturn(Flux.fromIterable(priceCalculationContext.getDiscountsOnProducts()));
    }

    @Given("there is a discount of {int} % for the item {string}")
    public void registerDiscountForArticle(int discount, String itemName) {
        UUID productId = priceCalculationContext.getItemByName(itemName);
        DiscountOnProduct discountOnProduct = new DiscountOnProduct(productId, BigDecimal.valueOf(discount));
        priceCalculationContext.addDiscount(discountOnProduct);
        when(discountPort.loadByProduct(productId)).thenReturn(Mono.just(discountOnProduct));
    }

    @Given("if I buy {int} article(s) of type {string}, the next one is free")
    public void registerDiscountForSetOfArticle(int threshold, String itemName) {
        UUID productId = priceCalculationContext.getItemByName(itemName);
        DiscountOnNextSameProduct discountOnItem = new DiscountOnNextSameProduct(productId, threshold, HUNDRED);
        priceCalculationContext.addDiscountsOnProduct(discountOnItem);
    }

    @Given("if I buy {int} article(s) of type {string}, the next one is {int} %")
    public void registerDiscountForSetOfArticle(int threshold, String itemName, int discount) {
        UUID productId = priceCalculationContext.getItemByName(itemName);
        DiscountOnNextSameProduct discountOnItem = new DiscountOnNextSameProduct(productId, threshold, BigDecimal.valueOf(discount));
        priceCalculationContext.addDiscountsOnProduct(discountOnItem);
    }
}
