package fr.deroffal.eshop.product.domain;


import java.util.UUID;

public record Product(UUID id, String name, String description) {

  public Product withId(UUID id) {
    return new Product(id, name, description);
  }
}
