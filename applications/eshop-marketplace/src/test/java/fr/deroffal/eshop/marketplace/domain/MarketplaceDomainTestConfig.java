package fr.deroffal.eshop.marketplace.domain;

import fr.deroffal.eshop.marketplace.domain.service.PricePort;
import fr.deroffal.eshop.marketplace.domain.service.ProductPort;
import fr.deroffal.eshop.marketplace.domain.service.StockPort;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static io.opentelemetry.api.OpenTelemetry.noop;

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

    @Bean
    public OpenTelemetry openTelemetry() {
        return noop();
    }
}
