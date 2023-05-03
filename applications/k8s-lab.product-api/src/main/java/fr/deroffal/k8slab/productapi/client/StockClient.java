package fr.deroffal.k8slab.productapi.client;

import fr.deroffal.k8slab.productapi.StockPort;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Profile("http")
//@FeignClient(value = "stockClient", url = "${stock.api.url}")
public interface StockClient extends StockPort {

  @GetMapping("/stock/{productName}")
  Stock getStockByProductName(@PathVariable String productName);
}
