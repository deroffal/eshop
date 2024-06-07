package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.DiscountOld;
import fr.deroffal.eshop.price.domain.model.DiscountOnItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface DiscountPort {
    Optional<DiscountOld> loadByItemName(String name);

    Mono<DiscountOnItem> loadByProduct(UUID product);
}
