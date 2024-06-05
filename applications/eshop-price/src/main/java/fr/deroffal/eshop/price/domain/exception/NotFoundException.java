package fr.deroffal.eshop.price.domain.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

  public NotFoundException(UUID product) {
    super("Product %s not found".formatted(product));
  }
}
