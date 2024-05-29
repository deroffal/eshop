package fr.deroffal.k8slab.productapi.domain;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PricePort {

  CompletableFuture<Price> getPriceByProduct(UUID product);
}
