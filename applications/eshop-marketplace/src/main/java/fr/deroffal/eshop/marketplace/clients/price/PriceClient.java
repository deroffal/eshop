package fr.deroffal.eshop.marketplace.clients.price;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "priceClient", url = "${marketplace.price.api.url}")
interface PriceClient {

    @GetMapping("/price/{product}")
    PriceResponse getPriceByProduct(@PathVariable UUID product);

    @PostMapping("/cart")
    PriceResponse getCartPrice(@RequestBody List<CartPriceItemRequest> items);
}
