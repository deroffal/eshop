package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfiguration {

    @Bean
    @ConditionalOnProperty(value = "marketplace.observability.enabled", havingValue = "true")
    public OpenTelemetry openTelemetry() {
        return GlobalOpenTelemetry.get();
    }

}
