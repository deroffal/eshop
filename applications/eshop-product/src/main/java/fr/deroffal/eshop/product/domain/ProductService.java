package fr.deroffal.eshop.product.domain;

import static java.util.function.UnaryOperator.identity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductPort repository;
  private final StockPort stockPort;
  private final PricePort pricePort;

  public ProductService(ProductPort repository, StockPort stockPort, PricePort pricePort) {
    this.repository = repository;
    this.stockPort = stockPort;
    this.pricePort = pricePort;
  }

  public Product save(Product product) {
    return repository.save(product);
  }

  public Optional<ProductDetail> getProductDetail(UUID id) {
    return repository.findById(id)
        .map(ProductDetail::forProduct)
        .map(product -> {

          // request upon product to get more information about it :
          var futures = List.of(
              pricePort.getPriceByProduct(product.product().id()).thenApply(ProductService::withPrice),
              stockPort.getStockByProduct(product.product().id()).thenApply(ProductService::withQuantity)
          );

          return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
              .thenApply(ignored -> futures.stream().map(CompletableFuture::join))
              .join()
              .reduce(identity(), Function::andThen)
              .apply(product);
        });

  }

  private static Function<ProductDetail, ProductDetail> withPrice(Price price) {
    return product -> product.withPrice(price);
  }

  private static Function<ProductDetail, ProductDetail> withQuantity(Stock stock) {
    return product -> product.withQuantity(stock.quantity());
  }

  public List<Product> findAll() {
    return repository.findAll();
  }
}
