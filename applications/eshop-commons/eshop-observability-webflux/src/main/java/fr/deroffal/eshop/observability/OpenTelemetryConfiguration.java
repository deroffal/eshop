package fr.deroffal.eshop.observability;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Default configuration class for OpenTelemetry.
 */
@Configuration
//@Profile(NOT_SKIP_OTEL) FIXME
public class OpenTelemetryConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return GlobalOpenTelemetry.get();
    }

}

