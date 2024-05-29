package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import java.util.Collection;

public record PriceCalculationRequest(Collection<CartItem> items) {
}
