package fr.deroffal.k8slab.productapi;

import fr.deroffal.k8slab.productapi.client.Stock;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductPort repository;
  private final StockPort stockPort;

  public ProductService(ProductPort repository, StockPort stockPort) {
    this.repository = repository;
    this.stockPort = stockPort;
  }

  public Product save(Product product) {
    return repository.save(product);
  }

  public Optional<ProductDetail> getProductDetail(String id) {
    return repository.findById(id)
        .map(product -> {
          Stock stock = stockPort.getStockByProductName(product.getName());
          return new ProductDetail(product, stock.quantity());
        });

  }

  public List<Product> findAll() {
    return repository.findAll();
  }
}
