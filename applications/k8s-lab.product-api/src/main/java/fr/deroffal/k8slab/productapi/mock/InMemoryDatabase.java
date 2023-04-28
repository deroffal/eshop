package fr.deroffal.k8slab.productapi.mock;

import fr.deroffal.k8slab.productapi.Product;
import fr.deroffal.k8slab.productapi.ProductPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!mongo")
public class InMemoryDatabase implements ProductPort  {

  private final List<Product> products = new ArrayList<>();

  @Override
  public Product save(Product product) {
    product.setId(UUID.randomUUID().toString());
    products.add(product);
    return product;
  }

  @Override
  public Optional<Product> findById(String id) {
    return products.stream().filter(product -> product.getId().equals(id)).findFirst();
  }

  @Override
  public List<Product> findAll() {
    return products;
  }
}
