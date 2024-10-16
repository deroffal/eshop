package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static io.opentelemetry.api.OpenTelemetry.noop;

@Configuration
@ComponentScan(basePackageClasses = OpenTelemetryConfiguration.class)
public class OpenTelemetryConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return noop();
    }

}
