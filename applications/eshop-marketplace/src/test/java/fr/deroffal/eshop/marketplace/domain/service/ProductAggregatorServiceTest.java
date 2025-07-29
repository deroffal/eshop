package fr.deroffal.eshop.marketplace.domain.service;

import fr.deroffal.eshop.marketplace.domain.MarketplaceDomainTestConfig;
import fr.deroffal.eshop.marketplace.domain.model.Price;
import fr.deroffal.eshop.marketplace.domain.model.Product;
import fr.deroffal.eshop.marketplace.domain.model.ProductDetail;
import fr.deroffal.eshop.marketplace.domain.model.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = MarketplaceDomainTestConfig.class)
@MarketplaceDomainTestConfig.MarketplaceDomainMocks
class ProductAggregatorServiceTest {

    @Autowired
    private ProductAggregatorService cut;

    @Autowired
    private ProductPort productMock;
    @Autowired
    private StockPort stockMock;
    @Autowired
    private PricePort priceMock;

    @Test
    void getProductDetail() {
        UUID productId = UUID.randomUUID();

        Product product = new Product(productId, "name", "description");
        when(productMock.getProduct(productId))
                .thenReturn(CompletableFuture.completedFuture(product));

        Price price = new Price(new BigDecimal("2000"), "EUR");
        when(priceMock.getPriceByProduct(productId))
                .thenReturn(CompletableFuture.completedFuture(price));

        Stock stock = new Stock(10);
        when(stockMock.getStockByProduct(productId))
                .thenReturn(CompletableFuture.completedFuture(stock));

        ProductDetail productDetail = cut.getProductDetail(productId);

        assertThat(productDetail.product()).isEqualTo(product);
        assertThat(productDetail.price()).isEqualTo(price);
        assertThat(productDetail.quantity()).isEqualTo(stock.quantity());
    }

}
