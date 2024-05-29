package fr.deroffal.k8slab.priceapi.domain.model;

import java.util.UUID;

public record CartItem(UUID product, long quantity) {
}
