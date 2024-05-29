package fr.deroffal.k8slab.productapi.api;

public record CreateProductRequest(String name, String description, double price) {
}
