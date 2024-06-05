package fr.deroffal.eshop.price.database.discount;

import static java.math.BigDecimal.TEN;

import fr.deroffal.eshop.price.domain.DiscountPort;
import fr.deroffal.eshop.price.domain.model.Discount;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DiscountInMemoryDatabase implements InitializingBean, DiscountPort {

  private final List<Discount> discounts = new ArrayList<>();

  @Override
  public Optional<Discount> loadByItemName(final String name) {
    return discounts.stream().filter(discount -> discount.itemName().equals(name)).findFirst();
  }

  @Override
  public void afterPropertiesSet() {
    discounts.add(new Discount("book", 2, TEN));
  }
}
