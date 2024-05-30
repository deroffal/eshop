package fr.deroffal.k8slab.priceapi.domain.model;

public record Discount(String itemName, int threshold, double amount) {

    public boolean isRelevantOn(final CartItem cartItem) {
        return cartItem.item().equals(itemName) && cartItem.quantity() >= threshold;
    }

    public double applyTo(final double price) {
        return price * (100 - amount()) / 100;
    }
}
