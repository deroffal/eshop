package fr.deroffal.eshop.marketplace.clients.product;

import fr.deroffal.eshop.marketplace.application.MapperConfiguration;
import fr.deroffal.eshop.marketplace.domain.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
interface ProductMapper {

    @Mapping(target = "withId", ignore = true)
    Product convert(ProductResponse response);
}
