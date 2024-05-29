package fr.deroffal.k8slab.productapi.repository;

import fr.deroffal.k8slab.productapi.domain.Product;
import fr.deroffal.k8slab.productapi.domain.ProductPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class InMemoryDatabase implements ProductPort {

  private final List<Product> products = new ArrayList<>(List.of(
      new Product(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), "product 1", "This is product 1."),
      new Product(UUID.fromString("e207a162-610b-4edf-953f-271b3adfbef7"), "product 2", "This is product 2."),
      new Product(UUID.fromString("9f84da1e-415b-4e3e-aad4-8012234f629d"), "product 3", "This is product 3."),
      new Product(UUID.fromString("ee31ce9b-661d-4ef5-a8f8-913215c59368"), "product 4", "This is product 4."),
      new Product(UUID.fromString("c3faabbc-1035-4ed4-93d5-af5af715013b"), "product 5", "This is product 5."),
      new Product(UUID.fromString("bc7254fd-6ab7-436f-ad79-1362af6f0497"), "product 6", "This is product 6."),
      new Product(UUID.fromString("d8f69d6c-d334-4de9-b406-3da5b3669bc2"), "product 7", "This is product 7."),
      new Product(UUID.fromString("cb0cb429-eac3-4a92-891c-9bb174b22076"), "product 8", "This is product 8.")
  ));

  @Override
  public Product save(Product product) {
    var newProduct = product.withId(UUID.randomUUID());
    products.add(newProduct);
    return newProduct;
  }

  @Override
  public Optional<Product> findById(UUID id) {
    return products.stream().filter(product -> product.id().equals(id)).findFirst();
  }

  @Override
  public List<Product> findAll() {
    return products;
  }
}
