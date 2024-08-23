package fr.deroffal.eshop.price.database.discount;

import fr.deroffal.eshop.price.domain.DiscountPort;
import fr.deroffal.eshop.price.domain.model.DiscountOnProduct;
import fr.deroffal.eshop.price.domain.model.DiscountOnNextSameProduct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.math.BigDecimal.TEN;

@Component
public class DiscountInMemoryDatabase implements InitializingBean, DiscountPort {

    private final List<DiscountOnProduct> discounts = new ArrayList<>();
    private final List<DiscountOnNextSameProduct> discountOnNextSameProduct = new ArrayList<>();

    @Override
    public Mono<DiscountOnProduct> loadByProduct(UUID product) {
        return discounts.stream().filter(discount -> discount.product().equals(product))
                .findAny()
                .map(Mono::just)
                .orElse(Mono.empty());
    }

    @Override
    public Flux<DiscountOnNextSameProduct> loadDiscountOnType() {
        return Flux.fromIterable(discountOnNextSameProduct);
    }


    @Override
    public void afterPropertiesSet() {
        discounts.add(new DiscountOnProduct(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), TEN));
        discountOnNextSameProduct.add(new DiscountOnNextSameProduct(UUID.fromString("bc7254fd-6ab7-436f-ad79-1362af6f0497"), 2, TEN));
    }
}
