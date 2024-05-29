package fr.deroffal.k8slab.priceapi.api;

import fr.deroffal.k8slab.priceapi.domain.PriceService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
class PriceHandler {

    private final PriceService priceService;
    private final ApiMapper apiMapper;

    public PriceHandler(PriceService priceService, ApiMapper apiMapper) {
        this.priceService = priceService;
        this.apiMapper = apiMapper;
    }

    public Mono<ServerResponse> getPrice(final ServerRequest request) {
        return Mono.just(UUID.fromString(request.pathVariable("product")))
                .flatMap(priceService::getItemPrice)
                .flatMap(price -> ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(BodyInserters.fromValue(apiMapper.toPriceResponse(price)))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
