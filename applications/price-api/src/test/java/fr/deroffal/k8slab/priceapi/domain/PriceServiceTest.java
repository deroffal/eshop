package fr.deroffal.k8slab.priceapi.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import fr.deroffal.k8slab.priceapi.domain.exception.NotFoundException;
import fr.deroffal.k8slab.priceapi.domain.model.Price;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
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
