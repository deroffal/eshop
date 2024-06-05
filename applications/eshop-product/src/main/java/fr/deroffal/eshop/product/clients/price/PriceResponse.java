package fr.deroffal.eshop.product.clients.price;

import java.math.BigDecimal;
import java.util.UUID;

record PriceResponse(UUID product, BigDecimal amount, String currency) {
}
