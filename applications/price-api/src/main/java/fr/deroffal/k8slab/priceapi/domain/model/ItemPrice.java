package fr.deroffal.k8slab.priceapi.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPrice(UUID product, String name, BigDecimal amount, String currency) {
}
