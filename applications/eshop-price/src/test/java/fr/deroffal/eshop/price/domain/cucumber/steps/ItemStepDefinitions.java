package fr.deroffal.eshop.price.domain.cucumber.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deroffal.eshop.price.domain.PriceStoragePort;
import fr.deroffal.eshop.price.domain.ProductPort;
import fr.deroffal.eshop.price.domain.model.Price;
import fr.deroffal.eshop.price.domain.model.Product;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class ItemStepDefinitions {

    @Autowired
    private PriceStoragePort priceStoragePort;

    @Autowired
    private ProductPort productPort;

    @Autowired
    private ObjectMapper cucumberObjectMapper;

    @Autowired
    private StepContext stepContext;

    @Given("the price of the following items :")
    public void initializePrices(final List<Map<String, String>> items) {
        items.forEach(item -> {
            UUID id = UUID.randomUUID();
            Price itemPrice = cucumberObjectMapper.convertValue(item, Price.class);
            stepContext.addItem(item.get("item"), id);
            when(priceStoragePort.getPrice(id)).thenReturn(Mono.just(itemPrice));
        });
    }

    @Given("the following items :")
    public void initializeItems(List<Map<String, String>> items) {
        items.forEach(item -> {
            UUID id = UUID.randomUUID();
            Price itemPrice = cucumberObjectMapper.convertValue(item, Price.class);
            stepContext.addItem(item.get("name"), id);
            when(priceStoragePort.getPrice(id)).thenReturn(Mono.just(itemPrice));

            Product product = cucumberObjectMapper.convertValue(item, Product.class);
            when(productPort.getProduct(id)).thenReturn(Mono.just(product));
        });
    }
}
