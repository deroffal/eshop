package fr.deroffal.eshop.product.api;

import fr.deroffal.eshop.product.domain.ProductService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Configuration
@ComponentScan(basePackageClasses = WebTestConfiguration.class)
public class WebTestConfiguration {

    @Target(TYPE)
    @Retention(RUNTIME)
    @MockitoBean(types = ProductService.class)
    public @interface EndpointTestMocks {

    }

}
