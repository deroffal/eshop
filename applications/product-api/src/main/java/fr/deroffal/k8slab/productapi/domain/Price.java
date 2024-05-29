package fr.deroffal.k8slab.productapi.domain;

import java.math.BigDecimal;

public record Price(BigDecimal amount, String currency) {
}
