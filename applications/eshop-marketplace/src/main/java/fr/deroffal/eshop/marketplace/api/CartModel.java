package fr.deroffal.eshop.marketplace.api;

import java.util.Collection;
import java.util.UUID;

public record CartModel(UUID id, Collection<ItemModel> items) {
}
