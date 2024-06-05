package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.Product;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductPort {

    Mono<Product> getProduct(UUID productId);
}
