package fr.deroffal.k8slab.productapi.api;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, String description, long quantity, Price price) {

  public record Price(BigDecimal amount, String currency) {}
}
