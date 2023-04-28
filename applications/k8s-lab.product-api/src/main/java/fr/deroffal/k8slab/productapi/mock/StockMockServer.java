package fr.deroffal.k8slab.productapi.mock;

import fr.deroffal.k8slab.productapi.StockPort;
import fr.deroffal.k8slab.productapi.client.Stock;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!http")
@Service
public class StockMockServer implements StockPort {

  @Override
  public Stock getStockByProductName(String name) {
    return new Stock(name, 10);
  }
}
