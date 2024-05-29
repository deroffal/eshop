package fr.deroffal.k8slab.productapi.clients.price;

import static java.util.concurrent.CompletableFuture.completedFuture;

import fr.deroffal.k8slab.productapi.domain.Price;
import fr.deroffal.k8slab.productapi.domain.PricePort;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

@Service
class PriceService implements PricePort {

  private final PriceClient priceClient;

  public PriceService(PriceClient priceClient) {
    this.priceClient = priceClient;
  }

  @Override
  public CompletableFuture<Price> getPriceByProduct(UUID product) {
    var response = priceClient.getPriceByProduct(product);
    return completedFuture(new Price(response.amount(), response.currency()));
  }
}
