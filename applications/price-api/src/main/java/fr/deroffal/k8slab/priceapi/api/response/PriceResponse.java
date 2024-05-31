package fr.deroffal.k8slab.priceapi.api.response;

import java.math.BigDecimal;
import java.util.UUID;

public record PriceResponse(BigDecimal amount, String currency) {
}
