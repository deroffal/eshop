package fr.deroffal.eshop.price.domain.model;

import java.util.UUID;

public record CartItem(UUID product, long quantity) {
}
