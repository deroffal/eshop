package fr.deroffal.k8slab.priceapi.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import fr.deroffal.k8slab.priceapi.api.request.ItemRequest;
import fr.deroffal.k8slab.priceapi.api.response.BasketPriceResponse;
import fr.deroffal.k8slab.priceapi.api.response.PriceResponse;
import fr.deroffal.k8slab.priceapi.domain.PriceCalculator;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
class BasketHandler {

  private final BasketMapper basketMapper;
  private final PriceCalculator priceCalculator;

  public Mono<ServerResponse> newBasket(final ServerRequest request) {
    return request.bodyToMono(new ParameterizedTypeReference<List<ItemRequest>>() {})
        .map(it -> it.stream().map(basketMapper::toBasketItem).toList())
        .map(priceCalculator::getPrice)
        .flatMap(this::toNewBasketServerResponse);
  }

  public Mono<ServerResponse> getPrice(final ServerRequest request) {
    return request.bodyToMono(UUID.class)
        .flatMap(priceCalculator::getItemPrice)
        .flatMap(price -> ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(BodyInserters.fromValue(basketMapper.toPriceResponse(price)))
        );
  }

  private Mono<ServerResponse> toNewBasketServerResponse(final Double price) {
    return ServerResponse.ok()
        .contentType(APPLICATION_JSON)
        .body(BodyInserters.fromValue(new BasketPriceResponse(price)));
  }
}

