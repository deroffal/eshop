package fr.deroffal.k8slab.priceapi.domain.cucumber.steps;

import static org.mockito.Mockito.doReturn;

import fr.deroffal.k8slab.priceapi.domain.ItemPort;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemStepDefinitions {

    @Autowired
    private ItemPort itemPort;

    @Given("the price of the following items :")
    public void initializePrices(final List<ItemPrice> items) {
        items.forEach(item -> doReturn(Optional.of(item)).when(itemPort).loadItem(item.name()));
    }

    @Given("a {string} costs {bigdecimal} {string}")
    public void initializePrices(final String itemName, final BigDecimal price, final String currency) {
        final ItemPrice item = new ItemPrice(itemName, price, currency);
        doReturn(Optional.of(item)).when(itemPort).loadItem(itemName);
    }
}
