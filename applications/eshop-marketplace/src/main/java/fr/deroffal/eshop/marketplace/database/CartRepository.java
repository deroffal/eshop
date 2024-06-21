package fr.deroffal.eshop.marketplace.database;

import fr.deroffal.eshop.marketplace.domain.CartPort;
import fr.deroffal.eshop.marketplace.domain.model.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CartRepository implements CartPort {

    private final List<Cart> carts = new ArrayList<>();

    @Override
    public Cart findById(UUID id) {
        return carts.stream().filter(it -> it.id().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Cart save(Cart cart) {
        int cartIndex = getCartIndex(cart.id());
        if (cartIndex == -1) {
            Cart newCart = cart.withId(UUID.randomUUID());
            carts.add(newCart);
            return newCart;
        }
        carts.remove(cartIndex);
        carts.add(cart);
        return cart;
    }

    @Override
    public void delete(UUID id) {
        int cartIndex = getCartIndex(id);
        carts.remove(cartIndex);
    }

    private int getCartIndex(UUID id) {
        int cartIndex = -1;
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).id().equals(id)) {
                cartIndex = i;
                break;
            }
        }
        return cartIndex;
    }
}
