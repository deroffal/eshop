package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.response.ErrorResponse;
import fr.deroffal.eshop.price.domain.PriceService;
import fr.deroffal.eshop.price.domain.exception.NotFoundException;
import fr.deroffal.eshop.price.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = WebFluxTestContextConfiguration.class)
class PriceRouteTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private PriceService priceService;

    @Nested
    @DisplayName("GET : /price/{product}")
    class GetPrice {
        @Test
        @DisplayName("returns 200 with price")
        void getPrice_returnsSuccess() {

            UUID product = UUID.randomUUID();

            Price expectedPrice = Price.euros(2000);
            when(priceService.getItemPrice(product)).thenReturn(Mono.just(expectedPrice));

            FluxExchangeResult<Price> result = webTestClient.get()
                    .uri("/price/{product}", product)
                    .exchange()
                    .expectStatus().isOk()
                    .returnResult(Price.class);

            StepVerifier.create(result.getResponseBody())
                    .expectNext(expectedPrice)
                    .verifyComplete();

        }

        @Test
        @DisplayName("returns 404 when product is not found")
        void getPrice_whenProductIsNotFound_returnsNotFound() {

            UUID product = UUID.randomUUID();

            when(priceService.getItemPrice(product)).thenReturn(Mono.error(new NotFoundException("Product %s not found".formatted(product))));

            FluxExchangeResult<ErrorResponse> result = webTestClient.get()
                    .uri("/price/{product}", product)
                    .exchange()
                    .expectStatus().isNotFound()
                    .returnResult(ErrorResponse.class);

            StepVerifier.create(result.getResponseBody())
                    .assertNext(error -> assertAll(
                            () -> assertThat(error.description()).isEqualTo("Product %s not found".formatted(product)),
                            () -> assertThat(error.time()).isNotNull()
                    ))
                    .verifyComplete();
        }

        @Test
        @DisplayName("returns 500 when internal error")
        void getPrice_whenInternalError_returnsInternalError() {

            UUID product = UUID.randomUUID();

            when(priceService.getItemPrice(product)).thenReturn(Mono.error(new Exception("An exception occurs !")));

            FluxExchangeResult<ErrorResponse> result = webTestClient.get()
                    .uri("/price/{product}", product)
                    .exchange()
                    .expectStatus().is5xxServerError()
                    .returnResult(ErrorResponse.class);

            StepVerifier.create(result.getResponseBody())
                    .assertNext(error -> assertAll(
                            () -> assertThat(error.description()).isEqualTo("An exception occurs !"),
                            () -> assertThat(error.time()).isNotNull()
                    ))
                    .verifyComplete();
        }
    }


}
