package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.domain.PriceCalculator;
import fr.deroffal.eshop.price.domain.PriceService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@ComponentScan(basePackageClasses = WebFluxTestContextConfiguration.class)
public class WebFluxTestContextConfiguration {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @MockitoBean(types = {PriceCalculator.class, PriceService.class})
    public @interface WebFluxTestMocks {

    }
}
