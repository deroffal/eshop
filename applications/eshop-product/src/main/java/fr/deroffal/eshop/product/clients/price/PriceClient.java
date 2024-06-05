package fr.deroffal.eshop.product.clients.price;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "priceClient", url = "${price.api.url}")
interface PriceClient {

  @GetMapping("/price/{product}")
  PriceResponse getPriceByProduct(@PathVariable UUID product);
}
