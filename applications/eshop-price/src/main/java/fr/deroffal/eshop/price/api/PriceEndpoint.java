package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.response.PriceResponse;
import fr.deroffal.eshop.price.domain.PriceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/price")
public class PriceEndpoint {

    private final PriceService priceService;
    private final ApiMapper apiMapper;

    public PriceEndpoint(PriceService priceService, ApiMapper apiMapper) {
        this.priceService = priceService;
        this.apiMapper = apiMapper;
    }

    @GetMapping("/{productId}")
    public Mono<ResponseEntity<PriceResponse>> getPrice(@PathVariable UUID productId) {
        return priceService.getItemPrice(productId)
                .map(apiMapper::toPriceResponse)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
