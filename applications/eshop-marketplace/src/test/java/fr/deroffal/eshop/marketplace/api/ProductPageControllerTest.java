package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.Price;
import fr.deroffal.eshop.marketplace.domain.model.Product;
import fr.deroffal.eshop.marketplace.domain.model.ProductDetail;
import fr.deroffal.eshop.marketplace.domain.service.ProductAggregatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = WebFluxApiConfiguration.class)
@WebMvcTest
class ProductPageControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private ProductAggregatorService service;


    @Test
    void getProduct() throws Exception {
        UUID id = UUID.randomUUID();
        ProductDetail productDetail = new ProductDetail(new Product(id, "name", "description"), 5, new Price(new BigDecimal("2000"), "EUR"));
        when(service.getProductDetail(id)).thenReturn(productDetail);

        client.perform(get("/product/{id}", id)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product.id").value(id.toString()))
                .andExpect(jsonPath("$.product.name").value("name"))
                .andExpect(jsonPath("$.product.description").value("description"))
                .andExpect(jsonPath("$.quantity").value(5))
                .andExpect(jsonPath("$.price.amount").value(2000))
                .andExpect(jsonPath("$.price.currency").value("EUR"));


    }

}
