package fr.deroffal.eshop.product.domain;

import java.util.UUID;

public record Product(UUID id, ProductType productType, String name, String description) {

    public Product withId(UUID id) {
        return new Product(id, productType, name, description);
    }
}
