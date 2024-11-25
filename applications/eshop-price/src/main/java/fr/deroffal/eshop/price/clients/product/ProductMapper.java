package fr.deroffal.eshop.price.clients.product;

import fr.deroffal.eshop.price.application.MapperConfiguration;
import fr.deroffal.eshop.price.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface ProductMapper {

    @Mapping(target = "type", source = "productType")
    Product convert(ProductModel productModel);
}
