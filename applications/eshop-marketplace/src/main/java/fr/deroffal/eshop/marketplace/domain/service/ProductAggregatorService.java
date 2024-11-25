package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.model.Price;
import fr.deroffal.eshop.marketplace.domain.model.Product;
import fr.deroffal.eshop.marketplace.domain.model.ProductDetail;
import fr.deroffal.eshop.marketplace.domain.model.Stock;
import fr.deroffal.eshop.observability.traces.Tracing;
import fr.deroffal.eshop.observability.traces.TracerFactory;
import io.opentelemetry.api.trace.Span;
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
    private final Tracing tracer;

    public ProductAggregatorService(ProductPort productPort, StockPort stockPort, PricePort pricePort, TracerFactory tracerFactory) {
        this.productPort = productPort;
        this.stockPort = stockPort;
        this.pricePort = pricePort;
        this.tracer = tracerFactory.newTracer(ProductAggregatorService.class);
    }

    public ProductDetail getProductDetail(UUID id) {
        return tracer.executeInSpan("product-detail-aggregation", span -> {
            return getProductDetail(id, span);
        });

    }

    private ProductDetail getProductDetail(UUID productId, Span span) {
        span.addEvent("product-detail-aggregation");
        var futures = List.of(
                productPort.getProduct(productId).thenApply(ProductAggregatorService::withProduct),
                pricePort.getPriceByProduct(productId).thenApply(ProductAggregatorService::withPrice),
                stockPort.getStockByProduct(productId).thenApply(ProductAggregatorService::withQuantity)
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
