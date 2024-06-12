package fr.deroffal.eshop.marketplace.clients.price;

import fr.deroffal.eshop.marketplace.application.MapperConfiguration;
import fr.deroffal.eshop.marketplace.domain.model.CartItem;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
interface PriceMapper {

    Price map(PriceResponse price);

    @Mapping(target = "item", source = "product")
    CartPriceItemRequest map(CartItem item);
}
