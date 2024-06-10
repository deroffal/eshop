package fr.deroffal.eshop.price.clients.product;

import fr.deroffal.eshop.price.clients.ClientConfiguration;
import fr.deroffal.eshop.price.domain.ProductPort;
import fr.deroffal.eshop.price.domain.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductService implements ProductPort {

    private final ClientConfiguration clientConfiguration;
    private final ProductMapper productMapper;

    public ProductService(ClientConfiguration clientConfiguration, ProductMapper productMapper) {
        this.clientConfiguration = clientConfiguration;
        this.productMapper = productMapper;
    }

    @Override
    public Mono<Product> getProduct(UUID productId) {
        return WebClient.create().get()
                .uri(clientConfiguration.product().url().resolve("/products/" + productId))
                .retrieve()
                .bodyToMono(ProductModel.class)
                .map(productMapper::convert);
    }
}
