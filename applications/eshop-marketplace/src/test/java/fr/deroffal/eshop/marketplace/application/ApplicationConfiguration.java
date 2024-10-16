package fr.deroffal.eshop.marketplace.application;

import fr.deroffal.eshop.marketplace.application.observability.OpenTelemetryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * This configuration class aims to be imported in all other TestConfiguration classes.
 * It will import all required configuration from `application` package.
 */
@Configuration
@Import(OpenTelemetryConfiguration.class)
public class ApplicationConfiguration {
}
