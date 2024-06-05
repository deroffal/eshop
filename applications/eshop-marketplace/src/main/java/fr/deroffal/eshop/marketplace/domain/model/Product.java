package fr.deroffal.eshop.marketplace.domain.model;


import java.util.UUID;

public record Product(UUID id, String name, String description) {

  public Product withId(UUID id) {
    return new Product(id, name, description);
  }
}
