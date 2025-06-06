package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.model.Product;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ProductPort {

    CompletableFuture<Product> getProduct(UUID product);
}
