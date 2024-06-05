package fr.deroffal.eshop.price.api;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import fr.deroffal.eshop.price.api.request.ItemRequest;
import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.model.Price;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

@ContextConfiguration(classes = WebFluxTestContextConfiguration.class)
@WebFluxTest
class RoutersTest {

  @Autowired
  private WebTestClient webTestClient;
  @Autowired
  private PriceCalculator priceCalculator;

  @Test
  @DisplayName("api : /cart")
  void postBasket() {
    //given the following items :
    UUID id1 = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();
    List<ItemRequest> items = List.of(new ItemRequest(id1.toString(), 1), new ItemRequest(id2.toString(), 2));

    doReturn(Price.euros(100822))
        .when(priceCalculator)
        .getPrice(argThat(
            basketItems ->
                basketItems.items().stream().anyMatch(item -> item.product().equals(id1) && item.quantity() == 1L)
                    && basketItems.items().stream()
                    .anyMatch(item -> item.product().equals(id2) && item.quantity() == 2L))
        );


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
}
