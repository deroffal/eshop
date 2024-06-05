package fr.deroffal.eshop.price.domain;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mandatory configuration to load Spring application for Cucumber tests.
 * We only load context inside "domain" package.
 */
@Configuration
@ComponentScan(basePackages = "fr.deroffal.eshop.price.domain")
public class DomainTestConfig {

    @MockBean
    private PriceStoragePort priceStoragePort;

    @MockBean
    private DiscountPort discountPort;

}
