package fr.deroffal.eshop.marketplace.clients.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "productClient", url = "${product.api.url}")
interface ProductClient {

    @GetMapping("/products/{product}")
    ProductResponse getProduct(@PathVariable UUID product);
}
