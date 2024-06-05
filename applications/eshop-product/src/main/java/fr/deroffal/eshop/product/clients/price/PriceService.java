package fr.deroffal.eshop.product.clients.price;

import static java.util.concurrent.CompletableFuture.completedFuture;

import fr.deroffal.eshop.product.domain.PricePort;
import fr.deroffal.eshop.product.domain.Price;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
class PriceService implements PricePort {

  private final PriceClient priceClient;

  public PriceService(PriceClient priceClient) {
    this.priceClient = priceClient;
  }

  @Override
  @Async
  public CompletableFuture<Price> getPriceByProduct(UUID product) {
    var response = priceClient.getPriceByProduct(product);
    return completedFuture(new Price(response.amount(), response.currency()));
  }
}
