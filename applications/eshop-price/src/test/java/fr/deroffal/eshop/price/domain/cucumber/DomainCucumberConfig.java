package fr.deroffal.eshop.price.domain.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deroffal.eshop.observability.traces.TracerFactory;
import fr.deroffal.eshop.price.domain.cucumber.steps.PriceCalculationContext;
import io.cucumber.spring.ScenarioScope;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Configuration
public class DomainCucumberConfig {

    @Bean
    @ScenarioScope
    public PriceCalculationContext stepContext() {
        return new PriceCalculationContext();
    }

    @Bean
    public ObjectMapper cucumberObjectMapper() {
        return new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
