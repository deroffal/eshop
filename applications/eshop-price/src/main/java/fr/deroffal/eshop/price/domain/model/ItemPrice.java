package fr.deroffal.eshop.price.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPrice(UUID product, BigDecimal amount, String currency) {
}
