package fr.deroffal.eshop.product.api;

import fr.deroffal.eshop.product.domain.ProductType;

public record CreateProductRequest(String name, ProductType productType, String description, double price) {
}
