package fr.deroffal.eshop.observability;

import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Test configuration injecting a fake implementation of OpenTelemetry (see {@link io.opentelemetry.api.OpenTelemetry#noop()}).
 * Use it associated with @ActiveProfiles(SKIP_OTEL)
 * <p>
 * Test configuration is using OpenTelemetry sdk.
 */
@Configuration
@ComponentScan(basePackageClasses = OpenTelemetryTestConfiguration.class)
public class OpenTelemetryTestConfiguration {

    @RegisterExtension
    private static final OpenTelemetryExtension otelTesting = OpenTelemetryExtension.create();

    @Bean
    public OpenTelemetryExtension otelTesting() {
        return otelTesting;
    }

}
