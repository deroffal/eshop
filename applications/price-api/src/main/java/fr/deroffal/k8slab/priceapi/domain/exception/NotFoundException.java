package fr.deroffal.k8slab.priceapi.domain.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException{

  public NotFoundException(UUID product) {
    super("Product %s not found".formatted(product));
  }
}
