package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.service.CartService;
import fr.deroffal.eshop.marketplace.domain.service.ProductAggregatorService;
import fr.deroffal.eshop.marketplace.observability.OpenTelemetryConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;

@Configuration
@Import(OpenTelemetryConfiguration.class)
@ComponentScan(basePackageClasses = WebTestConfiguration.class)
public class WebTestConfiguration {

    @MockBean
    private CartService service;

    @MockBean
    private ProductAggregatorService productAggregatorService;

}
