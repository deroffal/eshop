package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.model.Product;
import fr.deroffal.eshop.marketplace.domain.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ProductPort {


  Optional<Product> findById(UUID id);

  CompletableFuture<Product> getProduct(UUID product);
}
