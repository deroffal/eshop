package fr.deroffal.k8slab.productapi.clients.stock;

import java.util.UUID;

 record StockResponse(UUID name, long quantity) {
}
