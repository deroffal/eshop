package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.exception.NotFoundException;
import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import fr.deroffal.k8slab.priceapi.domain.model.Discount;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPriceV2;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PriceCalculator {

    private final ItemPort itemPort;
    private final DiscountPort discountPort;

    public double getPrice(final Collection<CartItem> cartItems) {
        return cartItems.stream().map(this::getPrice).reduce(0d, Double::sum);
    }

    public Mono<ItemPriceV2> getItemPrice(final UUID product) {
        return itemPort.getPrice(product)
            .switchIfEmpty(Mono.error(()-> new NotFoundException(product)));
    }

    private double getPrice(final CartItem cartItem) {
        final ItemPrice item = itemPort.loadItem(cartItem.item()).orElseThrow(() -> new IllegalArgumentException("Unknown item : " + cartItem.item()));
        double price = item.price() * cartItem.quantity();

        final Optional<Discount> itemDiscount = discountPort.loadByItemName(item.name());
        final boolean discountRelevant = itemDiscount.map(discount -> discount.isRelevantOn(cartItem)).orElse(false);
        if (discountRelevant) {
            price = itemDiscount.orElseThrow().applyTo(price);
        }

        return price;
    }
}
