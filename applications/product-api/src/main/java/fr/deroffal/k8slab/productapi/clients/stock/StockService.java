package fr.deroffal.k8slab.productapi.clients.stock;

import static java.util.concurrent.CompletableFuture.completedFuture;

import fr.deroffal.k8slab.productapi.domain.Stock;
import fr.deroffal.k8slab.productapi.domain.StockPort;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
class StockService implements StockPort {

  private final StockClient stockClient;

  public StockService(StockClient stockClient) {
    this.stockClient = stockClient;
  }

  @Override
  @Async
  public CompletableFuture<Stock> getStockByProduct(UUID product) {
    var response = stockClient.getStockByProduct(product);
    return completedFuture(new Stock(response.quantity()));
  }
}
