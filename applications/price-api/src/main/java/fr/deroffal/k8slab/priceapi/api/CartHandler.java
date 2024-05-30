package fr.deroffal.k8slab.priceapi.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import fr.deroffal.k8slab.priceapi.api.request.ItemRequest;
import fr.deroffal.k8slab.priceapi.api.response.CartPriceResponse;
import fr.deroffal.k8slab.priceapi.domain.CartCalculationRequest;
import fr.deroffal.k8slab.priceapi.domain.PriceCalculator;
import java.util.List;
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
        .map(it -> new CartCalculationRequest(it.stream().map(apiMapper::toBasketItem).toList()))
        .map(priceCalculator::getPrice)
        .flatMap(this::toNewBasketServerResponse);
  }

  private Mono<ServerResponse> toNewBasketServerResponse(final Double price) {
    return ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(new CartPriceResponse(price)));
  }
}

