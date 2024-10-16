package fr.deroffal.eshop.marketplace.clients.stock;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "stockClient", url = "${marketplace.stock.api.url}")
interface StockClient {

    @GetMapping("/stock/{product}")
    StockResponse getStockByProduct(@PathVariable UUID product);
}
