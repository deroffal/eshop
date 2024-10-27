package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.application.observability.EshopWebfluxTracer;
import fr.deroffal.eshop.price.application.observability.EshopWebfluxTracerFactory;
import fr.deroffal.eshop.price.domain.exception.CartException;
import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.DiscountOnNextSameProduct;
import fr.deroffal.eshop.price.domain.model.Price;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

import static fr.deroffal.eshop.price.domain.model.Price.ZERO_EURO;

@Service
public class PriceCalculator {

    private final PriceService priceStoragePort;
    private final DiscountService discountService;
    private final EshopWebfluxTracer tracer;

    public PriceCalculator(PriceService priceStoragePort, DiscountService discountService, EshopWebfluxTracerFactory tracerFactory) {
        this.priceStoragePort = priceStoragePort;
        this.discountService = discountService;
        this.tracer = tracerFactory.newTracer(PriceCalculator.class);
    }

    public Mono<Price> getPrice(final PriceCalculationRequest request) {
        return tracer.executeInSpan(
                "price-calculation",
                span -> span.addEvent("start-price-calculation", Attributes.of(
                        AttributeKey.stringKey("items"), String.join(", ", request.items().stream().map(CartItem::product).map(UUID::toString).collect(Collectors.joining()))
                )),
                () -> doGetPrice(request),
                (span, price) -> span.addEvent("price-calculated", Attributes.of(
                        AttributeKey.stringKey("currency"), price.currency(),
                        AttributeKey.stringKey("amount"), price.amount().toString()
                ))
        );
    }

    private Mono<Price> doGetPrice(final PriceCalculationRequest request) {
        Flux<DiscountOnNextSameProduct> discountsOnProducts = discountService.findDiscountsOnType();
        return Flux.fromIterable(request.items())
                .flatMap(cartItem ->
                        getUnitPriceFor(cartItem.product())
                                .flatMap(price -> {
                                    PriceContext priceContext = new PriceContext(cartItem.product(), price, cartItem.quantity(), discountsOnProducts);
                                    return calculatePrice(priceContext);
                                }))
                .reduce(ZERO_EURO, Price::add);
    }

    private static Mono<Price> calculatePrice(PriceContext priceContext) {
        var discountsOnProducts = priceContext.discountsOnProducts();
        return discountsOnProducts
                .filter(discount -> discount.isApplicableOn(priceContext.productId(), priceContext.quantity()))
                .map(discount -> discount.applyDiscount(priceContext.unitPrice(), priceContext.quantity()))
                .switchIfEmpty(Mono.just(priceContext.unitPrice().times(priceContext.quantity())))
                .reduce(ZERO_EURO, Price::add);
    }

    private Mono<Price> getUnitPriceFor(UUID product) {
        return priceStoragePort.getItemPrice(product)
                .switchIfEmpty(Mono.error(() -> new CartException("Product %s not found.".formatted(product))))
                .flatMap(price ->
                        discountService.findDiscountOnProduct(product).map(discount -> discount.applyOn(price))
                                .switchIfEmpty(Mono.just(price))
                                .map(discountedPrice -> {
                                    final BigDecimal amount = discountedPrice.amount();
                                    return new Price(amount, price.currency());
                                }));
    }

    private record PriceContext(UUID productId, Price unitPrice, long quantity,
                                Flux<DiscountOnNextSameProduct> discountsOnProducts) {


    }

}
