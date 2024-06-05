package fr.deroffal.eshop.price.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import fr.deroffal.eshop.price.api.request.ItemRequest;
import fr.deroffal.eshop.price.domain.PriceCalculationRequest;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import java.util.List;

import fr.deroffal.eshop.price.domain.model.Price;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
class CartHandler {

  private final ApiMapper apiMapper;
  private final PriceCalculator priceCalculator;

  public CartHandler(ApiMapper apiMapper, PriceCalculator priceCalculator) {
    this.apiMapper = apiMapper;
    this.priceCalculator = priceCalculator;
  }

  public Mono<ServerResponse> newCart(final ServerRequest request) {
    return request.bodyToMono(new ParameterizedTypeReference<List<ItemRequest>>() {})
        .map(it -> new PriceCalculationRequest(it.stream().map(apiMapper::toBasketItem).toList()))
        .flatMap(priceCalculator::getPrice)
        .flatMap((Price price) -> ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(BodyInserters.fromValue(price))
        );
  }
}

