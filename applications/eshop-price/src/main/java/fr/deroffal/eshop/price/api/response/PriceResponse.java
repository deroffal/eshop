package fr.deroffal.eshop.price.api.response;

import java.math.BigDecimal;

public record PriceResponse(BigDecimal amount, String currency) {
}
