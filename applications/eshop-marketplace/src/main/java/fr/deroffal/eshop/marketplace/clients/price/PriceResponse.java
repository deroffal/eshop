package fr.deroffal.eshop.marketplace.clients.price;

import java.math.BigDecimal;

record PriceResponse(BigDecimal amount, String currency) {
}
