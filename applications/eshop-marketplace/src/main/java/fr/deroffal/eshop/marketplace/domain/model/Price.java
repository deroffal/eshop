package fr.deroffal.eshop.marketplace.domain.model;

import java.math.BigDecimal;

public record Price(BigDecimal amount, String currency) {
}
