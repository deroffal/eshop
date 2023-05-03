package fr.deroffal.k8slab.productapi.api;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import fr.deroffal.k8slab.productapi.Product;
import fr.deroffal.k8slab.productapi.ProductDetail;
import fr.deroffal.k8slab.productapi.ProductService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  public Product createProduct(@RequestBody CreateProductRequest request) {
    Product product = requestToProduct(request);
    return service.save(product);
  }

  private static Product requestToProduct(CreateProductRequest request) {
    Product product = new Product();
    product.setName(request.name());
    product.setDescription(request.description());
    product.setPrice(request.price());
    return product;
  }

  @GetMapping("/{id}")
  public ProductDetail getProductById(@PathVariable String id) {
    LOGGER.debug("getProductById - " + id);
    return service.getProductDetail(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return service.findAll();
  }
}
