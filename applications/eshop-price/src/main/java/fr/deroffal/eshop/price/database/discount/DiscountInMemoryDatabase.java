package fr.deroffal.eshop.price.database.discount;

import static java.math.BigDecimal.TEN;

import fr.deroffal.eshop.price.domain.DiscountPort;

import java.util.*;

import fr.deroffal.eshop.price.domain.model.DiscountOnItem;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DiscountInMemoryDatabase implements InitializingBean, DiscountPort {

  private final List<DiscountOnItem> discounts = new ArrayList<>();

  @Override
  public Mono<DiscountOnItem> loadByProduct(UUID product) {
    return  discounts.stream().filter(discount -> discount.product().equals(product))
            .findAny()
            .map(Mono::just)
            .orElse(Mono.empty());
  }



  @Override
  public void afterPropertiesSet() {
    discounts.add(new DiscountOnItem(UUID.fromString("73aa5936-4410-47a7-96c3-80407b57d710"), TEN));
  }
}
