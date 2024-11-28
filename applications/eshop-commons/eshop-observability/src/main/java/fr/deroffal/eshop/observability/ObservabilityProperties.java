package fr.deroffal.eshop.observability;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration for observability.
 *
 * @param enabled Enable observability in your module.
 */
@ConfigurationProperties("eshop.observability")
public record ObservabilityProperties(boolean enabled) {
    //

}
