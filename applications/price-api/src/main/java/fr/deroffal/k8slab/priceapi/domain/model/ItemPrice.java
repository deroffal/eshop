package fr.deroffal.k8slab.priceapi.domain.model;

import java.math.BigDecimal;

public record ItemPrice(String name, BigDecimal amount, String currency) {
}
