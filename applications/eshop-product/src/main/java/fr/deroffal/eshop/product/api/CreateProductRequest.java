package fr.deroffal.eshop.product.api;

public record CreateProductRequest(String name, String description, double price) {
}
