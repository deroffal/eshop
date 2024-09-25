package fr.deroffal.eshop.product.api;


import fr.deroffal.eshop.product.domain.Product;
import fr.deroffal.eshop.product.domain.ProductService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static fr.deroffal.eshop.product.domain.ProductType.BIKE;
import static org.mockito.Mockito.when;

@WebMvcTest
@ContextConfiguration(classes = WebTestConfiguration.class)
abstract class ContractsTestClass {

    @Autowired
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        // GET /products/{id} : ok
        when(productService.getProductDetail(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710")))
                .thenReturn(Optional.of(new Product(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), BIKE, "bike 1", "This is product 1.")));

        // GET /products/{id} : not found
        when(productService.getProductDetail(UUID.fromString("e0ddf766-567d-4fbf-9f31-18f6085bc233"))).thenReturn(Optional.empty());
    }
}
