package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.request.ItemRequest;
import fr.deroffal.eshop.price.api.response.PriceResponse;
import fr.deroffal.eshop.price.application.MapperConfiguration;
import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface ApiMapper {

    @Mapping(source = "item", target = "product")
    CartItem toCartItem(ItemRequest itemRequest);

    PriceResponse toPriceResponse(Price itemPrice);
}
