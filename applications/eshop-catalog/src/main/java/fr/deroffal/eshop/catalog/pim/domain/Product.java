package fr.deroffal.eshop.catalog.pim.domain;

import java.util.UUID;

public record Product(UUID id, ProductType productType, String name, String description) {

    public Product withId(UUID id) {
        return new Product(id, productType, name, description);
    }
}
