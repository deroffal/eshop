package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import fr.deroffal.k8slab.priceapi.domain.model.Price;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface PriceStoragePort {

    Optional<ItemPrice> loadItem(String item);
    Mono<Price> getPrice(UUID product);
}
