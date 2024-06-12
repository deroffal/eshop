package fr.deroffal.eshop.marketplace.api;

import java.util.UUID;

public record ItemModel(UUID product, long quantity) {
}
