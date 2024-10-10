package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.service.CartService;
import fr.deroffal.eshop.marketplace.domain.service.ProductAggregatorService;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static io.opentelemetry.api.OpenTelemetry.noop;

@Configuration
@ComponentScan(basePackageClasses = WebTestConfiguration.class)
public class WebTestConfiguration {

    @MockBean
    private CartService service;

    @MockBean
    private ProductAggregatorService productAggregatorService;

    @Bean
    public OpenTelemetry openTelemetry() {
        return noop();
    }
}
