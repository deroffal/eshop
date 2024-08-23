package fr.deroffal.eshop.price.database.item;

import java.math.BigDecimal;
import java.util.UUID;

record ItemPrice(UUID product, BigDecimal amount, String currency) {
}
