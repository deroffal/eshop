package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.exception.NotFoundException;
import fr.deroffal.k8slab.priceapi.domain.model.Price;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PriceService {

    private final PriceStoragePort priceStoragePort;

    public PriceService(PriceStoragePort priceStoragePort) {
        this.priceStoragePort = priceStoragePort;
    }

    public Mono<Price> getItemPrice(final UUID product) {
        return priceStoragePort.getPrice(product)
                .switchIfEmpty(Mono.error(() -> new NotFoundException(product)));
    }
}
