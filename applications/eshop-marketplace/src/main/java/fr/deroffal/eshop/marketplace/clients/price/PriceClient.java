package fr.deroffal.eshop.marketplace.clients.price;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "priceClient", url = "${price.api.url}")
interface PriceClient {

  @GetMapping("/price/{product}")
  PriceResponse getPriceByProduct(@PathVariable UUID product);

  @PostMapping("/cart")
  PriceResponse getCartPrice(@RequestBody List<CartPriceItemRequest> items);
}
