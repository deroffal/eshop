package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static fr.deroffal.eshop.price.domain.model.Price.ZERO_EURO;

@Service
@RequiredArgsConstructor
public class PriceCalculator {

    private final PriceService priceStoragePort;
    private final DiscountPort discountPort;

    public Mono<Price> getPrice(final PriceCalculationRequest request) {
        return Flux.fromIterable(request.items())
                .flatMap(this::getPrice)
                .reduce(ZERO_EURO, Price::add);
    }

    private Mono<Price> getPrice(final CartItem cartItem) {
        return priceStoragePort.getItemPrice(cartItem.product())
                .switchIfEmpty(Mono.error(() -> new IllegalArgumentException("Unknown product : " + cartItem.product())))
                .map(item -> {
                    final BigDecimal amount = item.amount().multiply(BigDecimal.valueOf(cartItem.quantity()));
                    return new Price(amount, item.currency());
                });
    }

}
