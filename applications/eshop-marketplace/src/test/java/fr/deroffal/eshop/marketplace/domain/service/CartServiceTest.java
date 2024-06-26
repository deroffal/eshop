package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.CartPort;
import fr.deroffal.eshop.marketplace.domain.MarketplaceDomainTestConfig;
import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.model.CartItem;
import fr.deroffal.eshop.marketplace.domain.model.CartNotFoundException;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MarketplaceDomainTestConfig.class)
class CartServiceTest {

    @Autowired
    private CartService cut;

    @Autowired
    private CartPort cartMock;
    @Autowired
    private PricePort priceMock;

    @Nested
    @DisplayName("getByUuid")
    class GetByUuid {
        @Test
        @DisplayName("returns existing cart")
        void getByUuid() {
            UUID cartId = UUID.randomUUID();
            Cart cart = new Cart(cartId, List.of());
            when(cartMock.findById(cartId)).thenReturn(cart);

            Cart actual = cut.getByUuid(cartId);

            assertThat(actual).isEqualTo(cart);
        }

        @Test
        @DisplayName("returns null when cart does not exists")
        void getByUuid_noResult() {
            UUID cartId = UUID.randomUUID();
            when(cartMock.findById(cartId)).thenReturn(null);

            Cart actual = cut.getByUuid(cartId);

            assertThat(actual).isNull();
        }
    }

    @Nested
    @DisplayName("create")
    class Create {
        @Test
        @DisplayName("returns the created cart")
        void create() {
            Cart cart = new Cart(UUID.randomUUID(), List.of());
            when(cartMock.save(any())).thenReturn(cart);

            Cart actual = cut.create();

            assertThat(actual).isEqualTo(cart);
        }
    }

    @Test
    @DisplayName("delete delete object")
    void delete() {
        UUID cartId = UUID.randomUUID();

        cut.delete(cartId);

        verify(cartMock).delete(cartId);
    }

    @Nested
    @DisplayName("getPrice")
    class GetPrice {
        @Test
        @DisplayName("throws CartNotFoundException when cart does not exist")
        void getPrice_noCart() {
            UUID cartId = UUID.randomUUID();

            when(cartMock.findById(cartId)).thenReturn(null);

            assertThatThrownBy(() -> cut.getPrice(cartId))
                    .isInstanceOf(CartNotFoundException.class);

            verify(cartMock).findById(cartId);
        }

        @Test
        @DisplayName("returns the price of the cart")
        void getPrice() {
            UUID cartId = UUID.randomUUID();

            Cart cart = new Cart(cartId, List.of());
            when(cartMock.findById(cartId)).thenReturn(cart);
            Price price = new Price(new BigDecimal("2000"), "EUR");
            when(priceMock.getCartPrice(cart)).thenReturn(price);

            Price actual = cut.getPrice(cartId);

            assertThat(actual).isEqualTo(price);
        }
    }

    @Nested
    @DisplayName("updateCart")
    class UpdateCart {

        private final UUID cartId = UUID.randomUUID();
        private final Cart cart = new Cart(cartId, new ArrayList<>());

        @BeforeEach
        void setUp() {
            when(cartMock.findById(cartId)).thenReturn(cart);
            when(cartMock.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        }

        @Test
        @DisplayName("throws CartNotFoundException when cart does not exist")
        void updateCart_noCart() {
            CartItem item = new CartItem(UUID.randomUUID(), 1);

            UUID id = UUID.randomUUID();
            assertThatThrownBy(() -> cut.updateItem(id, item))
                    .isInstanceOf(CartNotFoundException.class);

            verify(cartMock).findById(id);
            verify(cartMock, never()).save(any());
        }

        @Test
        @DisplayName("when item does not belong to cart returns the updated cart")
        void updateCart_withNewItem() {
            UUID productId = UUID.randomUUID();
            CartItem item = new CartItem(productId, 1);

            Cart updatedCart = cut.updateItem(cartId, item);

            assertThat(updatedCart).isEqualTo(cart);

            var cartCaptor = ArgumentCaptor.forClass(Cart.class);
            verify(cartMock).save(cartCaptor.capture());

            Cart actualCart = cartCaptor.getValue();

            List<CartItem> items = actualCart.items();
            assertThat(items).hasSize(1);
            CartItem updatedItem = items.getFirst();

            assertThat(updatedItem.quantity()).isEqualTo(1);
            assertThat(updatedItem.product()).isEqualTo(productId);
        }

        @Nested
        @DisplayName("when item already in cart")
        class UpdateCartItem {

            @Test
            @DisplayName("increase the quantity")
            void updateCartItem_withPositiveValue_addValue() {
                UUID productId = UUID.randomUUID();
                cart.items().add(new CartItem(productId, 2));

                CartItem item = new CartItem(productId, 1);
                Cart updatedCart = cut.updateItem(cartId, item);

                var cartCaptor = ArgumentCaptor.forClass(Cart.class);
                verify(cartMock).save(cartCaptor.capture());

                assertThat(updatedCart).isEqualTo(cart);
                Cart actual = cartCaptor.getValue();

                List<CartItem> items = actual.items();
                assertThat(items).hasSize(1);
                CartItem updatedItem = items.getFirst();

                assertThat(updatedItem.quantity()).isEqualTo(3);
                assertThat(updatedItem.product()).isEqualTo(productId);
            }

            @Test
            @DisplayName("decrease the quantity")
            void updateCartItem_withNegativeValue_decreaseValue() {
                UUID productId = UUID.randomUUID();
                cart.items().add(new CartItem(productId, 2));

                Cart updatedCart = cut.updateItem(cartId, new CartItem(productId, -1));

                var cartCaptor = ArgumentCaptor.forClass(Cart.class);
                verify(cartMock).save(cartCaptor.capture());

                assertThat(updatedCart).isEqualTo(cart);
                Cart actual = cartCaptor.getValue();

                List<CartItem> items = actual.items();
                assertThat(items).hasSize(1);
                CartItem updatedItem = items.getFirst();

                assertThat(updatedItem.quantity()).isEqualTo(1);
                assertThat(updatedItem.product()).isEqualTo(productId);
            }

            @Test
            @DisplayName("remove item when there is no more")
            void updateCartItem_withNegativeValue_removeItem() {
                UUID productId = UUID.randomUUID();
                cart.items().add(new CartItem(productId, 2));

                Cart updatedCart = cut.updateItem(cartId, new CartItem(productId, -2));

                var cartCaptor = ArgumentCaptor.forClass(Cart.class);
                verify(cartMock).save(cartCaptor.capture());

                assertThat(updatedCart).isEqualTo(cart);

                Cart actual = cartCaptor.getValue();
                List<CartItem> items = actual.items();
                assertThat(items).hasSize(0);
            }

            @Test
            @DisplayName("remove item when there is no more")
            void updateCartItem_withQuantityLessThanExisting_simplyRemoveItem() {
                UUID productId = UUID.randomUUID();
                cart.items().add(new CartItem(productId, 2));

                Cart updatedCart = cut.updateItem(cartId, new CartItem(productId, -3));

                var cartCaptor = ArgumentCaptor.forClass(Cart.class);
                verify(cartMock).save(cartCaptor.capture());

                assertThat(updatedCart).isEqualTo(cart);

                Cart actual = cartCaptor.getValue();
                List<CartItem> items = actual.items();
                assertThat(items).hasSize(0);
            }
        }
    }

}
