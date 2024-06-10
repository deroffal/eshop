package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.DiscountOnItem;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DiscountPort {

    Mono<DiscountOnItem> loadByProduct(UUID product);
}
