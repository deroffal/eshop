package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static fr.deroffal.eshop.marketplace.application.Profiles.Otel.SKIP_OTEL;

@Configuration
@Profile(SKIP_OTEL)
public class OpenTelemetryNoopConfiguration {

    @Bean
    public OpenTelemetry openTelemetryNoop() {
        return OpenTelemetry.noop();
    }
}
