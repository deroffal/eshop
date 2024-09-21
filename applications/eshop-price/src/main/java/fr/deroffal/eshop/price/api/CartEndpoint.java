package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.request.ItemRequest;
import fr.deroffal.eshop.price.domain.PriceCalculationRequest;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.model.Price;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/cart")
public class CartEndpoint {


    private final ApiMapper apiMapper;
    private final PriceCalculator priceCalculator;

    public CartEndpoint(ApiMapper apiMapper, PriceCalculator priceCalculator) {
        this.apiMapper = apiMapper;
        this.priceCalculator = priceCalculator;
    }

    @PostMapping
    @ResponseStatus(OK)
    public Mono<Price> createCart(@RequestBody List<ItemRequest> items){
        PriceCalculationRequest priceCalculationRequest = new PriceCalculationRequest(items.stream().map(apiMapper::toCartItem).toList());
        return priceCalculator.getPrice(priceCalculationRequest);
    }

}
