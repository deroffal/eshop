package fr.deroffal.k8slab.priceapi.api;

import fr.deroffal.k8slab.priceapi.api.request.ItemRequest;
import fr.deroffal.k8slab.priceapi.api.response.PriceResponse;
import fr.deroffal.k8slab.priceapi.config.MapperConfiguration;
import fr.deroffal.k8slab.priceapi.domain.model.BasketItem;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPriceV2;
import java.math.BigDecimal;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
interface BasketMapper {

  BasketItem toBasketItem(ItemRequest itemRequest);

  PriceResponse toPriceResponse(ItemPriceV2 itemPrice);
}
