package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.CartItem;

import java.util.Collection;

public record PriceCalculationRequest(Collection<CartItem> items) {
}
