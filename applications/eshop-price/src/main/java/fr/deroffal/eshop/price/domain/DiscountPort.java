package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.Discount;
import java.util.Optional;

public interface DiscountPort {
    Optional<Discount> loadByItemName(String name);
}
