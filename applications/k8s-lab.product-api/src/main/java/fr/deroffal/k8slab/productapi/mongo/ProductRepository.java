package fr.deroffal.k8slab.productapi.mongo;

import org.springframework.context.annotation.Profile;

@Profile("mongo")
public interface ProductRepository
//    extends MongoRepository<Product, String>, ProductPort
{
}
