package fr.deroffal.eshop.marketplace.clients.product;

import fr.deroffal.eshop.marketplace.domain.model.Product;
import fr.deroffal.eshop.marketplace.domain.service.ProductPort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
class ProductService implements ProductPort {

    private final ProductClient productClient;
    private final ProductMapper productMapper;

    public ProductService(ProductClient productClient, ProductMapper productMapper) {
        this.productClient = productClient;
        this.productMapper = productMapper;
    }

    @Override
    @Async
    public CompletableFuture<Product> getProduct(UUID product) {
        ProductResponse response = productClient.getProduct(product);
        return CompletableFuture.completedFuture(productMapper.convert(response));
    }
}
