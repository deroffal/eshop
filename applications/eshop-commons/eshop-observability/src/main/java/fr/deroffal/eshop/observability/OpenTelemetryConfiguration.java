package fr.deroffal.eshop.observability;

import fr.deroffal.eshop.observability.traces.TracerFactory;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Default configuration class for OpenTelemetry.
 */
@Configuration
@AutoConfiguration
@ConditionalOnProperty(value = "eshop.observability.enabled", havingValue = "true")
public class OpenTelemetryConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return GlobalOpenTelemetry.get();
    }

    @Bean
    public TracerFactory tracerFactory(OpenTelemetry openTelemetry) {
        return new TracerFactory(openTelemetry);
    }

}
