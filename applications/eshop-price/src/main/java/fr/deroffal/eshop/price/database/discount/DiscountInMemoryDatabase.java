package fr.deroffal.eshop.price.database.discount;

import static java.math.BigDecimal.TEN;

import fr.deroffal.eshop.price.domain.DiscountPort;
import fr.deroffal.eshop.price.domain.model.DiscountOld;

import java.util.*;

import fr.deroffal.eshop.price.domain.model.DiscountOnItem;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DiscountInMemoryDatabase implements InitializingBean, DiscountPort {

  private final List<DiscountOld> discountOlds = new ArrayList<>();

  @Override
  public Optional<DiscountOld> loadByItemName(final String name) {
    return discountOlds.stream().filter(discountOld -> discountOld.itemName().equals(name)).findFirst();
  }

  @Override
  public Mono<DiscountOnItem> loadByProduct(UUID product) {
    return null;
  }



  @Override
  public void afterPropertiesSet() {
    discountOlds.add(new DiscountOld("book", 2, TEN));
  }
}
