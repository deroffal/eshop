package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPriceV2;
import java.util.Optional;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface ItemPort {

    Optional<ItemPrice> loadItem(String item);
    Mono<ItemPriceV2> getPrice(UUID product);
}
