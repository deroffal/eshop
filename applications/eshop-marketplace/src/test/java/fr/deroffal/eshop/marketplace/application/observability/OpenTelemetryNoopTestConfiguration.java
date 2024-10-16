package fr.deroffal.eshop.marketplace.application.observability;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import static fr.deroffal.eshop.marketplace.application.Profiles.Otel.SKIP_OTEL;

@Configuration
@ActiveProfiles(SKIP_OTEL)
@ComponentScan(basePackageClasses = OpenTelemetryConfiguration.class)
public class OpenTelemetryNoopTestConfiguration {

}
