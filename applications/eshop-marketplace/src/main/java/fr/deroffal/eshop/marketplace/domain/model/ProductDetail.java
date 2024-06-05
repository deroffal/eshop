package fr.deroffal.eshop.marketplace.domain.model;

public record ProductDetail(Product product, long quantity, Price price) {

    public ProductDetail withProduct(Product product) {
        return new ProductDetail(product, quantity, price);
    }

    public ProductDetail withQuantity(long quantity) {
        return new ProductDetail(product, quantity, price);
    }

    public ProductDetail withPrice(Price price) {
        return new ProductDetail(product, quantity, price);
    }
}
