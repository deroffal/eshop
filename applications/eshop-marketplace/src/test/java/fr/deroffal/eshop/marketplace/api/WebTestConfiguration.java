package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.application.ApplicationConfiguration;
import fr.deroffal.eshop.marketplace.domain.service.CartService;
import fr.deroffal.eshop.marketplace.domain.service.ProductAggregatorService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@Import(ApplicationConfiguration.class)
@ComponentScan(basePackageClasses = WebTestConfiguration.class)
public class WebTestConfiguration {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @MockitoBean(types = {CartService.class, ProductAggregatorService.class})
    public @interface WebTestMocks {

    }

}
