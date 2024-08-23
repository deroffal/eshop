package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.DiscountOnNextSameProduct;
import fr.deroffal.eshop.price.domain.model.DiscountOnProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface DiscountPort {

    Mono<DiscountOnProduct> loadByProduct(UUID product);

    Flux<DiscountOnNextSameProduct> loadDiscountOnType();
}
