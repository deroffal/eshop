package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.request.ItemRequest;
import fr.deroffal.eshop.price.api.response.PriceResponse;
import fr.deroffal.eshop.price.config.MapperConfiguration;
import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
interface ApiMapper {

  @Mapping(source = "item", target = "product")
  CartItem toBasketItem(ItemRequest itemRequest);

  PriceResponse toPriceResponse(Price itemPrice);
}
