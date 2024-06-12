package fr.deroffal.eshop.marketplace.domain;

import fr.deroffal.eshop.marketplace.domain.model.Cart;

import java.util.UUID;

public interface CartPort {

    Cart findById(UUID id);

    Cart save(Cart cart);

    void delete(UUID id);
}
