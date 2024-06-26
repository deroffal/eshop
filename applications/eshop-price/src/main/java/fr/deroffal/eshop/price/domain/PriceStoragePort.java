package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.ItemPrice;
import fr.deroffal.eshop.price.domain.model.Price;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

public interface PriceStoragePort {

    Mono<Price> getPrice(UUID product);
}
