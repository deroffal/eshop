package fr.deroffal.k8slab.productapi;

import java.util.List;
import java.util.Optional;

public interface ProductPort {

  Product save(Product product);

  Optional<Product> findById(String id);

  List<Product> findAll();
}
