package fr.deroffal.eshop.marketplace.clients.product;

import fr.deroffal.eshop.marketplace.application.MapperConfiguration;
import fr.deroffal.eshop.marketplace.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
interface ProductMapper {

    Product convert(ProductResponse response);
}
