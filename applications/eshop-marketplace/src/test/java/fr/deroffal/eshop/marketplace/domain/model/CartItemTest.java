package fr.deroffal.eshop.marketplace.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CartItemTest {

    @Test
    void cartItem() {
        UUID id = UUID.randomUUID();
        int quantity = 5;
        CartItem item = new CartItem(id, quantity);

        Assertions.assertThat(item.product()).isEqualTo(id);
        Assertions.assertThat(item.quantity()).isEqualTo(quantity);

    }


}
