package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.request.ItemRequest;
import fr.deroffal.eshop.price.api.response.ErrorResponse;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.exception.CartException;
import fr.deroffal.eshop.price.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@WebFluxTest
@ContextConfiguration(classes = WebFluxTestContextConfiguration.class)
class CartEndpointTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PriceCalculator priceCalculator;

    @Test
    @DisplayName("POST /cart")
    void createCart_returnsSuccess() {
        //given the following articles :
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<ItemRequest> items = List.of(new ItemRequest(id1.toString(), 1), new ItemRequest(id2.toString(), 2));

        when(priceCalculator.getPrice(argThat(
                basketItems ->
                        basketItems.items().stream().anyMatch(item -> item.product().equals(id1) && item.quantity() == 1L)
                                && basketItems.items().stream()
                                .anyMatch(item -> item.product().equals(id2) && item.quantity() == 2L))
        )).thenReturn(Mono.just(Price.euros(100822)));


        var result = webTestClient.post()
                .uri("/cart").accept(APPLICATION_JSON)
                .body(fromValue(items))
                .exchange()
                .expectStatus().isOk()
                .returnResult(Price.class);

        StepVerifier.create(result.getResponseBody())
                .expectNext(Price.euros(100822))
                .verifyComplete();

    }

    @Test
    @DisplayName("POST /cart")
    void createCart_whenUnknownProduct_returnsBadRequest() {
        //given the following articles :
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        List<ItemRequest> items = List.of(new ItemRequest(id1.toString(), 1), new ItemRequest(id2.toString(), 2));

        when(priceCalculator.getPrice(argThat(
                basketItems ->
                        basketItems.items().stream().anyMatch(item -> item.product().equals(id1) && item.quantity() == 1L)
                                && basketItems.items().stream()
                                .anyMatch(item -> item.product().equals(id2) && item.quantity() == 2L))
        )).thenReturn(Mono.error(new CartException("Product %s is unknown".formatted(id1))));


        var result = webTestClient.post()
                .uri("/cart").accept(APPLICATION_JSON)
                .body(fromValue(items))
                .exchange()
                .expectStatus().isBadRequest()
                .returnResult(ErrorResponse.class);

        StepVerifier.create(result.getResponseBody())
                .assertNext(error -> assertAll(
                        () -> assertThat(error.description()).isEqualTo("Product %s is unknown".formatted(id1)),
                        () -> assertThat(error.time()).isNotNull()
                ))
                .verifyComplete();

    }
}
