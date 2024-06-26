package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.CartPort;
import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.model.CartItem;
import fr.deroffal.eshop.marketplace.domain.model.CartNotFoundException;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CartService {

    private final CartPort port;
    private final PricePort pricePort;

    public CartService(CartPort port, PricePort pricePort) {
        this.port = port;
        this.pricePort = pricePort;
    }

    public Cart getByUuid(UUID uuid) {
        return port.findById(uuid);
    }

    public Cart create() {
        return port.save(new Cart(null, new ArrayList<>()));
    }

    public Cart updateItem(UUID cartId, CartItem item) {
        Cart cart = port.findById(cartId);
        if (cart != null) {
            int itemIndex = -1;
            List<CartItem> items = cart.items();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).product().equals(item.product())) {
                    itemIndex = i;
                    break;
                }
            }

            if (itemIndex == -1) {
                items.add(item);
            } else {
                CartItem cartItem = items.get(itemIndex);
                items.remove(itemIndex);
                long newQuantity = cartItem.quantity() + item.quantity();
                if(newQuantity > 0){
                    items.add(cartItem.withQuantity(newQuantity));
                }
            }
            return port.save(cart);
        }
        throw new CartNotFoundException();
    }

    public void delete(UUID cart) {
        port.delete(cart);
    }

    public Price getPrice(UUID id) {
        Cart cart = port.findById(id);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        return pricePort.getCartPrice(cart);
    }
}
