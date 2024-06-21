package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.model.Stock;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface StockPort {

    CompletableFuture<Stock> getStockByProduct(UUID product);
}
