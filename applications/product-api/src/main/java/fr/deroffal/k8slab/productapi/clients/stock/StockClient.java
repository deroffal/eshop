package fr.deroffal.k8slab.productapi.clients.stock;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "stockClient", url = "${stock.api.url}")
interface StockClient {

  @GetMapping("/stock/{product}")
  StockResponse getStockByProduct(@PathVariable UUID product);
}
