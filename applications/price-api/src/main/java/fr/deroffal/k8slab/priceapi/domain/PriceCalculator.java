package fr.deroffal.k8slab.priceapi.domain;

import static fr.deroffal.k8slab.priceapi.domain.model.Price.ZERO_EURO;

import fr.deroffal.k8slab.priceapi.domain.exception.NotFoundException;
import fr.deroffal.k8slab.priceapi.domain.model.CartItem;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPrice;
import fr.deroffal.k8slab.priceapi.domain.model.ItemPriceV2;
import fr.deroffal.k8slab.priceapi.domain.model.Price;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PriceCalculator {

  private final ItemPort itemPort;
  private final DiscountPort discountPort;

  public Price getPrice(final PriceCalculationRequest request) {
    return request.items().stream().map(this::getPrice).reduce(ZERO_EURO, Price::add);
  }

  private Price getPrice(final CartItem cartItem) {
    final ItemPrice item = itemPort.loadItem(cartItem.item())
        .orElseThrow(() -> new IllegalArgumentException("Unknown item : " + cartItem.item()));

    final BigDecimal amount = item.amount().multiply(BigDecimal.valueOf(cartItem.quantity()));

    var finalAmount = discountPort.loadByItemName(item.name())
        .filter(discount -> discount.isRelevantOn(cartItem))
        .map(discount -> discount.applyTo(amount))
        .orElse(amount);

    return new Price(finalAmount, item.currency());
  }

  public Mono<ItemPriceV2> getItemPrice(final UUID product) {
    return itemPort.getPrice(product)
        .switchIfEmpty(Mono.error(() -> new NotFoundException(product)));
  }
}
