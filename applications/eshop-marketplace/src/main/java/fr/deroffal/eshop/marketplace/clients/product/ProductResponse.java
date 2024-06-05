package fr.deroffal.eshop.marketplace.clients.product;

import java.util.UUID;

record ProductResponse(UUID id, String name, String type, String description) {
}
