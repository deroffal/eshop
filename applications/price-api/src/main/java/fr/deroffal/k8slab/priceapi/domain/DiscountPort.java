package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.model.Discount;
import java.util.Optional;

public interface DiscountPort {
    Optional<Discount> loadByItemName(String name);
}
