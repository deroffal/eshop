package fr.deroffal.k8slab.priceapi.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPriceV2(UUID product, BigDecimal amount, String currency) {
}
