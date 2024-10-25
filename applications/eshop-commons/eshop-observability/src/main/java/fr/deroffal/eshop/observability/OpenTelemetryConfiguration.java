package fr.deroffal.eshop.observability;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static fr.deroffal.eshop.observability.Profiles.NOT_SKIP_OTEL;

/**
 * Default configuration class for OpenTelemetry.
 */
@Configuration
@Profile(NOT_SKIP_OTEL)
public class OpenTelemetryConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return GlobalOpenTelemetry.get();
    }

}
