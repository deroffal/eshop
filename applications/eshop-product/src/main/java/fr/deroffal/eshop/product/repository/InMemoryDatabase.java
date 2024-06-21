package fr.deroffal.eshop.product.repository;

import fr.deroffal.eshop.product.domain.Product;
import fr.deroffal.eshop.product.domain.ProductRepository;
import fr.deroffal.eshop.product.domain.ProductType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static fr.deroffal.eshop.product.domain.ProductType.*;

@Service
public class InMemoryDatabase implements ProductRepository {

    private final List<Product> products = new ArrayList<>(List.of(
            newProduct("73aa5936-4410-47a7-96c3-80407b57d710", BIKE, "bike 1", "This is product 1."),
            newProduct("e207a162-610b-4edf-953f-271b3adfbef7", SHOES, "shoes 1", "This is product 2."),
            newProduct("9f84da1e-415b-4e3e-aad4-8012234f629d", BIKE, "bike 2", "This is product 3."),
            newProduct("ee31ce9b-661d-4ef5-a8f8-913215c59368", BOOK, "book 1", "This is product 4."),
            newProduct("c3faabbc-1035-4ed4-93d5-af5af715013b", BIKE, "bike 3", "This is product 5."),
            newProduct("bc7254fd-6ab7-436f-ad79-1362af6f0497", BOOK, "book 2", "This is product 6."),
            newProduct("d8f69d6c-d334-4de9-b406-3da5b3669bc2", BOOK, "book 3", "This is product 7."),
            newProduct("cb0cb429-eac3-4a92-891c-9bb174b22076", SHOES, "shoes 2", "This is product 8.")
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

    private static Product newProduct(String id, ProductType productType, String name, String description) {
        return new Product(UUID.fromString(id), productType, name, description);
    }
}
