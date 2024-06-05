package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.model.Price;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface PricePort {

  CompletableFuture<Price> getPriceByProduct(UUID product);
}
