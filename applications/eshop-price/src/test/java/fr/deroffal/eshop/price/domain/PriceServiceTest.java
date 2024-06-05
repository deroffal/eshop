package fr.deroffal.eshop.price.domain;

import static org.mockito.Mockito.when;

import fr.deroffal.eshop.price.domain.exception.NotFoundException;
import fr.deroffal.eshop.price.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

@SpringBootTest(classes = DomainTestConfig.class)
class PriceServiceTest {

    @Autowired
    private PriceService cut;

    @Autowired
    private PriceStoragePort priceStoragePort;

    @Test
    void getItemPrice_returnsError_whenItemNotFound() {
        UUID product = UUID.randomUUID();
        when(priceStoragePort.getPrice(product)).thenReturn(Mono.empty());

        Mono<Price> itemPrice = cut.getItemPrice(product);

        StepVerifier.create(itemPrice)
                .expectErrorMatches(throwable ->
                        throwable instanceof NotFoundException nfe
                                && nfe.getMessage().equals("Product %s not found".formatted(product)))
                .verify();
    }
}
