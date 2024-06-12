package fr.deroffal.eshop.marketplace.domain.model;

import java.util.UUID;

public record CartItem(UUID product, long quantity) {

    public CartItem withQuantity(long quantity) {
        return new CartItem(product, quantity);
    }
}
