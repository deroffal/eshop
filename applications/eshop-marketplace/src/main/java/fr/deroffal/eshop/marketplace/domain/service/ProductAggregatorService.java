package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.model.Price;
import fr.deroffal.eshop.marketplace.domain.model.Product;
import fr.deroffal.eshop.marketplace.domain.model.ProductDetail;
import fr.deroffal.eshop.marketplace.domain.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.util.function.UnaryOperator.identity;

@Service
public class ProductAggregatorService {

    private final ProductPort productPort;
    private final StockPort stockPort;
    private final PricePort pricePort;

    public ProductAggregatorService(ProductPort productPort, StockPort stockPort, PricePort pricePort) {
        this.productPort = productPort;
        this.stockPort = stockPort;
        this.pricePort = pricePort;
    }

    public ProductDetail getProductDetail(UUID id) {
        var futures = List.of(
                productPort.getProduct(id).thenApply(ProductAggregatorService::withProduct),
                pricePort.getPriceByProduct(id).thenApply(ProductAggregatorService::withPrice),
                stockPort.getStockByProduct(id).thenApply(ProductAggregatorService::withQuantity)
        );

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                .thenApply(ignored -> futures.stream().map(CompletableFuture::join))
                .join()
                .reduce(identity(), Function::andThen)
                .apply(new ProductDetail(null, 0, null));

    }

    private static Function<ProductDetail, ProductDetail> withProduct(Product product) {
        return detail -> detail.withProduct(product);
    }

    private static Function<ProductDetail, ProductDetail> withPrice(Price price) {
        return detail -> detail.withPrice(price);
    }

    private static Function<ProductDetail, ProductDetail> withQuantity(Stock stock) {
        return detail -> detail.withQuantity(stock.quantity());
    }

}
