package fr.deroffal.eshop.marketplace.clients.stock;

import fr.deroffal.eshop.marketplace.domain.model.Stock;
import fr.deroffal.eshop.marketplace.domain.service.StockPort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

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
