package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.domain.PriceCalculationRequest;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.PriceService;
import fr.deroffal.eshop.price.domain.exception.CartException;
import fr.deroffal.eshop.price.domain.exception.NotFoundException;
import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.Price;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@WebFluxTest
@ContextConfiguration(classes = WebFluxTestContextConfiguration.class)
public abstract class ContractsTestClass {

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceCalculator priceCalculator;

    @Autowired
    private ReactiveWebApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
        // GET /price/{id} : ok
        when(priceService.getItemPrice(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710")))
                .thenReturn(Mono.just(Price.euros(1200)));

        // GET /price/{id} : not found
        doThrow(new NotFoundException("Product e0ddf766-567d-4fbf-9f31-18f6085bc233 not found."))
                .when(priceService)
                .getItemPrice(UUID.fromString("e0ddf766-567d-4fbf-9f31-18f6085bc233"));

        // POST /cart : ok
        doReturn(Mono.just(Price.euros(3000)))
                .when(priceCalculator)
                .getPrice(new PriceCalculationRequest(List.of(
                        new CartItem(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), 2),
                        new CartItem(UUID.fromString("c3faabbc-1035-4ed4-93d5-af5af715013b"), 3)
                )));

        // POST /cart : product not found
        doThrow(new CartException("Product e0ddf766-567d-4fbf-9f31-18f6085bc233 not found."))
                .when(priceCalculator)
                .getPrice(new PriceCalculationRequest(List.of(
                        new CartItem(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), 2),
                        new CartItem(UUID.fromString("c3faabbc-1035-4ed4-93d5-af5af715013b"), 3),
                        new CartItem(UUID.fromString("e0ddf766-567d-4fbf-9f31-18f6085bc233"), 1)
                )));

        RestAssuredWebTestClient.applicationContextSetup(applicationContext);
    }

}
