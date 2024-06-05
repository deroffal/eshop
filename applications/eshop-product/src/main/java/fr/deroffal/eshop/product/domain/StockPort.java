package fr.deroffal.eshop.product.domain;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface StockPort {

  CompletableFuture<Stock> getStockByProduct(UUID product);
}
