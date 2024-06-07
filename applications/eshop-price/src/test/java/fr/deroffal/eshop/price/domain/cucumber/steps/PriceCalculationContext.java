package fr.deroffal.eshop.price.domain.cucumber.steps;

import fr.deroffal.eshop.price.domain.model.DiscountOnItem;

import java.util.*;

public class PriceCalculationContext {

    private final Map<String, UUID> itemsByName = new HashMap<>();
    private final List<DiscountOnItem> discounts = new ArrayList<>();

    public UUID getItemByName(final String name) {
        return itemsByName.get(name);
    }

    public void addItem(final String name, final UUID uuid) {
        itemsByName.put(name, uuid);
    }

    public void addDiscount(final DiscountOnItem discount) {
        discounts.add(discount);
    }

    public List<DiscountOnItem> getDiscounts() {
        return discounts;
    }
}
