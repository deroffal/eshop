package fr.deroffal.k8slab.productapi.mongo;

import fr.deroffal.k8slab.productapi.Product;
import fr.deroffal.k8slab.productapi.ProductPort;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

@Profile("mongo")
public interface ProductRepository extends MongoRepository<Product, String>, ProductPort {
}
