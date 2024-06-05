package fr.deroffal.eshop.product.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductPort {

  Product save(Product product);

  Optional<Product> findById(UUID id);

  List<Product> findAll();
}
