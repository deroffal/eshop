package fr.deroffal.k8slab.priceapi.domain;

import fr.deroffal.k8slab.priceapi.domain.exception.NotFoundException;
import fr.deroffal.k8slab.priceapi.domain.model.BasketItem;
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

    public double getPrice(final Collection<BasketItem> basketItems) {
        return basketItems.stream().map(this::getPrice).reduce(0d, Double::sum);
    }

    public Mono<ItemPriceV2> getItemPrice(final UUID product) {
        return itemPort.getPrice(product);
    }

    private double getPrice(final BasketItem basketItem) {
        final ItemPrice item = itemPort.loadItem(basketItem.item()).orElseThrow(() -> new IllegalArgumentException("Unknown item : " + basketItem.item()));
        double price = item.price() * basketItem.quantity();

        final Optional<Discount> itemDiscount = discountPort.loadByItemName(item.name());
        final boolean discountRelevant = itemDiscount.map(discount -> discount.isRelevantOn(basketItem)).orElse(false);
        if (discountRelevant) {
            price = itemDiscount.orElseThrow().applyTo(price);
        }

        return price;
    }
}
