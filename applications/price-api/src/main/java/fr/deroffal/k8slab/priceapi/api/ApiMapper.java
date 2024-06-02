package fr.deroffal.k8slab.priceapi.api;

import fr.deroffal.k8slab.priceapi.api.request.ItemRequest;
import fr.deroffal.k8slab.priceapi.api.response.PriceResponse;
import fr.deroffal.k8slab.priceapi.config.MapperConfiguration;
import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import fr.deroffal.k8slab.priceapi.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
interface ApiMapper {

  @Mapping(source = "item", target = "product")
  CartItem toBasketItem(ItemRequest itemRequest);

  PriceResponse toPriceResponse(Price itemPrice);
}
