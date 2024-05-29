package fr.deroffal.k8slab.priceapi.domain.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.deroffal.k8slab.priceapi.domain.cucumber.steps.StepContext;
import io.cucumber.spring.ScenarioScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Configuration
public class DomainCucumberConfig {

    @Bean
    @ScenarioScope
    public StepContext stepContext() {
        return new StepContext();
    }

    @Bean
    public ObjectMapper cucumberObjectMapper() {
        return new ObjectMapper()
                .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
