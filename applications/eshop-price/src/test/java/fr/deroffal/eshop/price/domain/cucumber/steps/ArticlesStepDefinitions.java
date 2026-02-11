package fr.deroffal.eshop.price.domain.cucumber.steps;

import tools.jackson.databind.ObjectMapper;
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

public class ArticlesStepDefinitions {

    @Autowired
    private PriceStoragePort priceStoragePort;

    @Autowired
    private ProductPort productPort;

    @Autowired
    private ObjectMapper cucumberObjectMapper;

    @Autowired
    private PriceCalculationContext priceCalculationContext;

    @Given("the following articles :")
    public void initializeItems(List<Map<String, String>> items) {
        items.forEach(item -> {
            UUID id = UUID.randomUUID();
            Price itemPrice = cucumberObjectMapper.convertValue(item, Price.class);
            priceCalculationContext.addItem(item.get("name"), id);
            when(priceStoragePort.getPrice(id)).thenReturn(Mono.just(itemPrice));

            Product product = cucumberObjectMapper.convertValue(item, Product.class);
            when(productPort.getProduct(id)).thenReturn(Mono.just(product));
        });
    }
}
