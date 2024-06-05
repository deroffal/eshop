package fr.deroffal.eshop.product.domain;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> getProductDetail(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
