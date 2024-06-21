package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.application.MapperConfiguration;
import fr.deroffal.eshop.marketplace.domain.model.Cart;
import fr.deroffal.eshop.marketplace.domain.model.CartItem;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = MapperConfiguration.class)
public interface CartMapper {

    CartModel toModel(Cart cart);

    ItemModel toModel(CartItem cartItem);

    @Mapping(target = "withQuantity", ignore = true)
    CartItem toItem(ItemModel item);

    @Mapping(target = "cart", source = "id")
    CartPriceModel from(UUID id, Price price);
}
