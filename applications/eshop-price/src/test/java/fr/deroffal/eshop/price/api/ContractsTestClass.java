package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.domain.PriceService;
import fr.deroffal.eshop.price.domain.exception.NotFoundException;
import fr.deroffal.eshop.price.domain.model.Price;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.web.reactive.context.ReactiveWebApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = WebFluxTestContextConfiguration.class)
public abstract class ContractsTestClass {

    @Autowired
    private PriceService priceService;

    @Autowired
    private ReactiveWebApplicationContext applicationContext;

    @BeforeEach
    public void setUp() {
//        PriceService priceService = Mockito.mock(PriceService.class);
        when(priceService.getItemPrice(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710")))
                .thenReturn(Mono.just(Price.euros(1200)));

        doThrow(new NotFoundException("boum !")).when(priceService).getItemPrice(UUID.fromString("e0ddf766-567d-4fbf-9f31-18f6085bc233"));

//        RestAssuredWebTestClient.standaloneSetup(
//                new PriceEndpoint(priceService, new ApiMapperImpl())
//        );

        RestAssuredWebTestClient.applicationContextSetup(applicationContext);

    }

}
