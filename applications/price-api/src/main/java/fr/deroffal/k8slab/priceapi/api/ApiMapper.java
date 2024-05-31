package fr.deroffal.k8slab.priceapi.api;

import fr.deroffal.k8slab.priceapi.api.request.ItemRequest;
import fr.deroffal.k8slab.priceapi.api.response.PriceResponse;
import fr.deroffal.k8slab.priceapi.config.MapperConfiguration;
import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
interface ApiMapper {

  CartItem toBasketItem(ItemRequest itemRequest);

  PriceResponse toPriceResponse(ItemPrice itemPrice);
}
