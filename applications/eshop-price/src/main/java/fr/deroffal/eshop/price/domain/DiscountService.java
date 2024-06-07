package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.domain.model.CartItem;
import fr.deroffal.eshop.price.domain.model.DiscountOnItem;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DiscountService {

    private final DiscountPort discountPort;

    public DiscountService(DiscountPort discountPort) {
        this.discountPort = discountPort;
    }

    public Mono<DiscountOnItem> findDiscountOnItem(CartItem cartItem) {
        return discountPort.loadByProduct(cartItem.product())
                .filter(discount -> discount.product().equals(cartItem.product()));
    }
}
