package fr.deroffal.eshop.marketplace.observability;

import static io.opentelemetry.api.OpenTelemetry.noop;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = OpenTelemetryConfiguration.class)
public class OpenTelemetryConfiguration {

    @Bean
    public OpenTelemetry openTelemetry() {
        return noop();
    }

}
