package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.PriceService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WebFluxTestContextConfiguration.class)
public class WebFluxTestContextConfiguration {

    @MockBean
    private PriceCalculator priceCalculator;

    @MockBean
    private PriceService priceService;
}
