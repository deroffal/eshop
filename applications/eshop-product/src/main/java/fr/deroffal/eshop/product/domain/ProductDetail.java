package fr.deroffal.eshop.product.domain;

public record ProductDetail(Product product, long quantity, Price price) {

  public static ProductDetail forProduct(Product product) {
    return new ProductDetail(product, 0, null);
  }

  public ProductDetail withQuantity(long quantity) {
    return new ProductDetail(product, quantity, price);
  }

  public ProductDetail withPrice(Price price) {
    return new ProductDetail(product, quantity, price);
  }
}
