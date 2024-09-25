package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.response.PriceResponse;
import fr.deroffal.eshop.price.domain.PriceService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/price")
public class PriceEndpoint {

    private final PriceService priceService;
    private final ApiMapper apiMapper;

    public PriceEndpoint(PriceService priceService, ApiMapper apiMapper) {
        this.priceService = priceService;
        this.apiMapper = apiMapper;
    }

    @ResponseStatus(OK)
    @GetMapping("/{productId}")
    public Mono<PriceResponse> getPrice(@PathVariable UUID productId) {
        return priceService.getItemPrice(productId)
                .map(apiMapper::toPriceResponse);
    }

}
