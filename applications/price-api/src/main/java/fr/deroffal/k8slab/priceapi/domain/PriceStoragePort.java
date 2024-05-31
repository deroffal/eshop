package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface PriceStoragePort {

    Optional<ItemPrice> loadItem(String item);
    Mono<ItemPrice> getPrice(UUID product);
}
