package fr.deroffal.eshop.observability;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static fr.deroffal.eshop.observability.Profiles.SKIP_OTEL;


/**
 * Configuration class allowing to run code dedicated to instrumentation without having OpenTelemetry enabled.
 * This class is used for tests.
 */
@Configuration
@Profile(SKIP_OTEL)
public class OpenTelemetryNoopConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return OpenTelemetry.noop();
    }
}
