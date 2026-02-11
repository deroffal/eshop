package fr.deroffal.eshop.price.domain.cucumber;

import fr.deroffal.eshop.price.domain.cucumber.steps.PriceCalculationContext;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class DomainCucumberConfig {

    @Bean
    @ScenarioScope
    public PriceCalculationContext stepContext() {
        return new PriceCalculationContext();
    }

    @Bean
    public ObjectMapper cucumberObjectMapper() {
        return new JsonMapper();
    }
}
