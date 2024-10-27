package fr.deroffal.eshop.price.config;

import fr.deroffal.eshop.observability.OpenTelemetryTestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * This configuration class aims to be imported in all other TestConfiguration classes.
 * It will import all required configuration from `application` package.
 */
@Configuration
@Import(OpenTelemetryTestConfiguration.class)
public class ApplicationConfiguration {
}
