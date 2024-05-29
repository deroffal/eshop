package fr.deroffal.k8slab.stockapi;

import java.util.UUID;

public record Stock(UUID name, long quantity) {
}
