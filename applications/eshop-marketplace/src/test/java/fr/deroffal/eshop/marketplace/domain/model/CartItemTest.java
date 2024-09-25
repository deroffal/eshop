package fr.deroffal.eshop.marketplace.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CartItemTest {

    @Test
    void cartItem() {
        UUID id = UUID.randomUUID();
        int quantity = 5;
        CartItem item = new CartItem(id, quantity);

        assertThat(item.product()).isEqualTo(id);
        assertThat(item.quantity()).isEqualTo(quantity);
    }

    @Test
    void withId(){
        UUID id = UUID.randomUUID();

        CartItem item =  new CartItem(id, 0).withQuantity(5);

        assertThat(item.product()).isEqualTo(id);
        assertThat(item.quantity()).isEqualTo(5);
    }


}
