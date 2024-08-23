package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.DiscountOnProduct;
import fr.deroffal.eshop.price.domain.model.DiscountOnNextSameProduct;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DiscountService {

    private final DiscountPort discountPort;

    public DiscountService(DiscountPort discountPort) {
        this.discountPort = discountPort;
    }

    public Mono<DiscountOnProduct> findDiscountOnProduct(UUID product) {
        return discountPort.loadByProduct(product);
    }

    public Flux<DiscountOnNextSameProduct> findDiscountsOnType() {
        return discountPort.loadDiscountOnType();
    }
}
