package fr.deroffal.eshop.price.domain;

import fr.deroffal.eshop.price.application.ApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mandatory configuration to load Spring application for Cucumber tests.
 * We only load context inside "domain" package.
 */
@Configuration
@Import(ApplicationConfiguration.class)
@ComponentScan(basePackages = "fr.deroffal.eshop.price.domain")
public class DomainTestConfig {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @MockitoBean(types = {PriceStoragePort.class, DiscountPort.class, ProductPort.class})
    public @interface DomainTestMocks {

    }

}
