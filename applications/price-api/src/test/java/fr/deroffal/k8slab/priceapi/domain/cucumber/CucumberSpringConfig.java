package fr.deroffal.k8slab.priceapi.domain.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deroffal.k8slab.priceapi.domain.DiscountPort;
import fr.deroffal.k8slab.priceapi.domain.PriceStoragePort;
import fr.deroffal.k8slab.priceapi.domain.cucumber.steps.StepContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Mandatory configuration to load Spring application for Cucumber tests.
 * We only load context inside "domain" package.
 */
@Configuration
@ComponentScan(basePackages = "fr.deroffal.k8slab.priceapi.domain")
public class CucumberSpringConfig {

    @MockBean
    private PriceStoragePort priceStoragePort;

    @MockBean
    private DiscountPort discountPort;

    @Bean
    public ObjectMapper cucumberObjectMapper() {
        return new ObjectMapper()
//                .configure(FAIL_ON_IGNORED_PROPERTIES, false)
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
//                .configure(IGNORE_M, false)
                ;
    }

    @Bean
    public StepContext stepContext() {
        return new StepContext();
    }
}
