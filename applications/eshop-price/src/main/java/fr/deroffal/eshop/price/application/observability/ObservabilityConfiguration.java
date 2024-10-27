package fr.deroffal.eshop.price.application.observability;

import fr.deroffal.eshop.observability.OpenTelemetryConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = OpenTelemetryConfiguration.class)
public class ObservabilityConfiguration {
}
