package fr.deroffal.eshop.marketplace.domain.model;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public record Cart(UUID id, List<CartItem> items) {
    public Cart withId(UUID id) {
        return new Cart(id, items);
    }
}
