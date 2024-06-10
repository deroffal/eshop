package fr.deroffal.eshop.price.clients.product;

import fr.deroffal.eshop.price.clients.ClientTestConfiguration;
import fr.deroffal.eshop.price.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.MediaType.APPLICATION_JSON;

@MockServerTest("price.client.product.url=http://localhost:${mockServerPort}")
@SpringBootTest(classes = ClientTestConfiguration.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private MockServerClient client;

    @Test
    void getDetailedAthlete() {
        UUID productId = UUID.fromString("9749e8ae-69a3-4c15-a7eb-10073298487e");

        client.when(
                        request()
                                .withMethod("GET")
                                .withPath("/products/9749e8ae-69a3-4c15-a7eb-10073298487e")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withContentType(APPLICATION_JSON)
                                .withBody(
                                        //language=json
                                        """
                                                {
                                                  "id": "9749e8ae-69a3-4c15-a7eb-10073298487e",
                                                  "productType": "bike",
                                                  "name": "Bike900",
                                                  "description": "The latest bike, for pro !"
                                                }
                                                """
                                ));

        Mono<Product> product = productService.getProduct(productId);

        StepVerifier.create(product)
                .assertNext(actual -> assertAll(
                        ()-> assertThat(actual.id()).isEqualTo(productId),
                        ()-> assertThat(actual.name()).isEqualTo("Bike900"),
                        ()-> assertThat(actual.type()).isEqualTo("bike")
                ))
                .verifyComplete();
    }

}
