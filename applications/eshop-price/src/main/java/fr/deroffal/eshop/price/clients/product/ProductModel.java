package fr.deroffal.eshop.price.clients.product;

import java.util.UUID;

public record ProductModel(UUID id, String productType, String name, String description) {
}
