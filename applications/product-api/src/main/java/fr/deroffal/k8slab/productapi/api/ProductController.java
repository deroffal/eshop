package fr.deroffal.k8slab.productapi.api;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fr.deroffal.k8slab.productapi.api.ProductResponse.Price;
import fr.deroffal.k8slab.productapi.domain.Product;
import fr.deroffal.k8slab.productapi.domain.ProductDetail;
import fr.deroffal.k8slab.productapi.domain.ProductService;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request) {
    Product product = requestToProduct(request);
    Product save = service.save(product);
    return ResponseEntity.created(URI.create("/products/" + save.id())).body(save);
  }

  private static Product requestToProduct(CreateProductRequest request) {
    return new Product(null, request.name(), request.description());
  }

  @GetMapping("/{id}")
  public ProductResponse getProductById(@PathVariable UUID id) {
    LOGGER.debug("getProductById - " + id);
    return service.getProductDetail(id)
        .map(detail -> {
          var product = detail.product();
          var price = detail.price();
          return new ProductResponse(product.id(), product.name(), product.description(), detail.quantity(), new Price(price.amount(), price.currency()));
        })
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return service.findAll();
  }
}
