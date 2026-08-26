package fr.deroffal.eshop.catalog.pim.api;

import fr.deroffal.eshop.catalog.pim.domain.ProductType;

public record CreateProductRequest(String name, ProductType productType, String description, double price) {
}
