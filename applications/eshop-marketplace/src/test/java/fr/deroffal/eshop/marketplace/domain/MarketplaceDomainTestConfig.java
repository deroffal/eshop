package fr.deroffal.eshop.marketplace.domain;

import fr.deroffal.eshop.marketplace.domain.service.PricePort;
import fr.deroffal.eshop.marketplace.domain.service.ProductPort;
import fr.deroffal.eshop.marketplace.domain.service.StockPort;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "fr.deroffal.eshop.marketplace.domain")
public class MarketplaceDomainTestConfig {

    @MockBean
    private ProductPort productPort;
    @MockBean
    private StockPort stockPort;
    @MockBean
    private PricePort pricePort;
    @MockBean
    private CartPort port;
}
