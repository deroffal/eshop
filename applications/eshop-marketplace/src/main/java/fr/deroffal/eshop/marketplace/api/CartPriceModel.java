package fr.deroffal.eshop.marketplace.api;

import java.math.BigDecimal;
import java.util.UUID;

public record CartPriceModel(UUID cart, BigDecimal amount, String currency) {
}
