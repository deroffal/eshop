package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.model.CartItem;
import fr.deroffal.eshop.marketplace.domain.model.CartNotFoundException;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = WebTestConfiguration.class)
@WebMvcTest
@WebTestConfiguration.WebTestMocks
class CartControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private CartService service;

    @Test
    void newCart() throws Exception {
        UUID cartId = UUID.randomUUID();
        Cart cart = new Cart(cartId, List.of());

        when(service.create()).thenReturn(cart);

        client.perform(post("/cart/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/cart/" + cartId))
                .andExpect(jsonPath("$.id").value(cartId.toString()));
    }

    @Test
    void deleteCart() throws Exception {
        UUID cartId = UUID.randomUUID();

        client.perform(delete("/cart/{id}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(service).delete(cartId);
    }

    @Nested
    @DisplayName("updateItem")
    class updateItem {
        @Test
        @DisplayName("returns updated cart")
        void updateItem_returnsUpdatedCart() throws Exception {
            UUID cartId = UUID.randomUUID();
            UUID productId = UUID.randomUUID();
            CartItem cartItem = new CartItem(productId, 5L);
            Cart cart = new Cart(cartId, List.of(cartItem));

            when(service.updateItem(eq(cartId), any())).thenReturn(cart);

            client.perform(put("/cart/{id}", cartId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"product\":\"" + productId + "\", \"quantity\":5}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(cartId.toString()))
                    .andExpect(jsonPath("$.items[0].product").value(productId.toString()))
                    .andExpect(jsonPath("$.items[0].quantity").value(5L));
        }

        @Test
        @DisplayName("returns bad request when unknown cart")
        void updateItem_unknwownCart() throws Exception {
            UUID cartId = UUID.randomUUID();
            UUID productId = UUID.randomUUID();

            doThrow(new CartNotFoundException())
                    .when(service).updateItem(eq(cartId), any());


            client.perform(put("/cart/{id}", cartId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"product\":\"" + productId + "\", \"quantity\":5}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    void getCart() throws Exception {
        UUID cartId = UUID.randomUUID();
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        CartItem cartItem1 = new CartItem(productId1, 2L);
        CartItem cartItem2 = new CartItem(productId2, 3L);
        Cart cart = new Cart(cartId, List.of(cartItem1, cartItem2));

        when(service.getByUuid(cartId)).thenReturn(cart);

        client.perform(get("/cart/{id}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cartId.toString()))
                .andExpect(jsonPath("$.items[0].product").value(productId1.toString()))
                .andExpect(jsonPath("$.items[0].quantity").value(2L))
                .andExpect(jsonPath("$.items[1].product").value(productId2.toString()))
                .andExpect(jsonPath("$.items[1].quantity").value(3L));
    }


    @Test
    void getPrice() throws Exception {
        UUID cartId = UUID.randomUUID();
        BigDecimal amount = new BigDecimal("2000");
        String currency = "EUR";

        when(service.getPrice(cartId)).thenReturn(new Price(amount, currency));

        client.perform(get("/cart/{id}/price", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cart").value(cartId.toString()))
                .andExpect(jsonPath("$.amount").value(amount))
                .andExpect(jsonPath("$.currency").value(currency));
    }
}
