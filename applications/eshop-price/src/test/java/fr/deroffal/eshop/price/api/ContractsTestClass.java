package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.domain.PriceService;
import fr.deroffal.eshop.price.domain.model.Price;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.when;

public class ContractsTestClass {

    @BeforeEach
    public void setUp() {
        PriceService priceService = Mockito.mock(PriceService.class);
        when(priceService.getItemPrice(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710")))
                .thenReturn(Mono.just(Price.euros(1200)));

        RestAssuredWebTestClient.standaloneSetup(
                new PriceEndpoint(priceService, new ApiMapperImpl())
        );

    }

}
