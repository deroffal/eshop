package fr.deroffal.k8slab.priceapi.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import fr.deroffal.k8slab.priceapi.domain.PriceCalculator;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
class PriceHandler {

  private final PriceCalculator priceCalculator;
  private final ApiMapper apiMapper;

  public PriceHandler(PriceCalculator priceCalculator, ApiMapper apiMapper) {
    this.priceCalculator = priceCalculator;
    this.apiMapper = apiMapper;
  }

  public Mono<ServerResponse> getPrice(final ServerRequest request) {
    return Mono.just(UUID.fromString(request.pathVariable("product")))
        .flatMap(priceCalculator::getItemPrice)
        .flatMap(price -> ServerResponse.ok()
            .contentType(APPLICATION_JSON)
            .body(BodyInserters.fromValue(apiMapper.toPriceResponse(price)))
        )
        .switchIfEmpty(ServerResponse.notFound().build());
  }
}
