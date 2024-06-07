package fr.deroffal.eshop.price.domain.cucumber.steps;

import fr.deroffal.eshop.price.domain.DiscountPort;
import fr.deroffal.eshop.price.domain.model.DiscountOnItem;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class DiscountStepDefinition {

    @Autowired
    private DiscountPort discountPort;

    @Autowired
    private PriceCalculationContext priceCalculationContext;

//    private final List<DiscountOnItem>

    //    @Given("a discount of {bigdecimal} % from {int} {string}")
//    public void registerDiscount(BigDecimal amount, int threshold, String name) {
//        final DiscountOld discountOld = new DiscountOld(name, threshold, amount);
//        doReturn(Optional.of(discountOld)).when(discountPort).loadByItemName(name);
//    }

    @Before
    public void before() {
//        when(discountPort.load()).thenReturn(Flux.fromIterable(priceCalculationContext.getDiscounts()));
    }

    @Given("there is a discount of {int} % for the item {string}")
    public void registerDiscountForItem(int discount, String itemName) {
        UUID productId = priceCalculationContext.getItemByName(itemName);
        DiscountOnItem discountOnItem = new DiscountOnItem(productId, BigDecimal.valueOf(discount));
        priceCalculationContext.addDiscount(discountOnItem);
        when(discountPort.loadByProduct(productId)).thenReturn(Mono.just(discountOnItem));
    }

}
