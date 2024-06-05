package fr.deroffal.eshop.product.domain;

import java.math.BigDecimal;

public record Price(BigDecimal amount, String currency) {
}
