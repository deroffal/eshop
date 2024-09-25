package fr.deroffal.eshop.product.api;

import fr.deroffal.eshop.product.domain.ProductService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WebTestConfiguration.class)
public class WebTestConfiguration {

    @MockBean
    private ProductService service;

}
