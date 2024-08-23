package fr.deroffal.eshop.price.domain.cucumber.steps;

import fr.deroffal.eshop.price.domain.model.DiscountOnProduct;
import fr.deroffal.eshop.price.domain.model.DiscountOnNextSameProduct;

import java.util.*;

public class PriceCalculationContext {

    private final Map<String, UUID> itemsByName = new HashMap<>();
    private final List<DiscountOnProduct> discounts = new ArrayList<>();
    private final List<DiscountOnNextSameProduct> discountsOnProducts = new ArrayList<>();

    public UUID getItemByName(final String name) {
        return itemsByName.get(name);
    }

    public void addItem(final String name, final UUID uuid) {
        itemsByName.put(name, uuid);
    }

    public void addDiscount(final DiscountOnProduct discount) {
        discounts.add(discount);
    }

    public List<DiscountOnProduct> getDiscounts() {
        return discounts;
    }

    public void addDiscountsOnProduct(final DiscountOnNextSameProduct discount) {
        discountsOnProducts.add(discount);
    }

    public List<DiscountOnNextSameProduct> getDiscountsOnProducts() {
        return discountsOnProducts;
    }
}
