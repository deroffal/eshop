package fr.deroffal.k8slab.productapi;

import fr.deroffal.k8slab.productapi.client.Stock;

public interface StockPort {

  Stock getStockByProductName(String name);
}
