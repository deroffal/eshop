package fr.deroffal.k8slab.priceapi;

import java.math.BigDecimal;
import java.util.UUID;

public record Price(UUID product, BigDecimal amount, String currency) {
}
