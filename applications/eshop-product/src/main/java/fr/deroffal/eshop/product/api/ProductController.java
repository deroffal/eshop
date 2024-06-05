package fr.deroffal.eshop.product.api;

import fr.deroffal.eshop.product.domain.Product;
import fr.deroffal.eshop.product.domain.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        return new Product(null, request.productType(), request.name(), request.description());
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id) {
        LOGGER.debug("getProductById - {}" , id);
        return service.getProductDetail(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.findAll();
    }
}
