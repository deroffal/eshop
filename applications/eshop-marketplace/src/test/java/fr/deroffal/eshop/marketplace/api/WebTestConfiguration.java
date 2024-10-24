package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.application.ApplicationConfiguration;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import fr.deroffal.eshop.marketplace.domain.service.ProductAggregatorService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;

@Configuration
@Import(ApplicationConfiguration.class)
@ComponentScan(basePackageClasses = WebTestConfiguration.class)
public class WebTestConfiguration {

    @MockBean
    private CartService service;

    @MockBean
    private ProductAggregatorService productAggregatorService;

}
