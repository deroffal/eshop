package fr.deroffal.eshop.observability;

import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Test configuration is using OpenTelemetry sdk.
 */
@Configuration
@ComponentScan(basePackageClasses = OpenTelemetryTestConfiguration.class)
public class OpenTelemetryTestConfiguration {

    /**
     * See <a href="https://github.com/open-telemetry/opentelemetry-java/blob/main/sdk/testing/src/test/java/io/opentelemetry/sdk/testing/junit5/OpenTelemetryExtensionTest.java">OpenTelemetryExtensionTest</a>.
     */
    @RegisterExtension
    private static final OpenTelemetryExtension otelTesting = OpenTelemetryExtension.create();

    @Bean
    public OpenTelemetryExtension otelTesting() {
        return otelTesting;
    }

//    /**
//     * An easier alternative to configure OpenTelemetry for tests. It can be used instead of the testing sdk.
//     * @return a noop implementation of OpenTelemetry.
//     */
//    @Bean
//    public OpenTelemetry openTelemetry() {
//        return OpenTelemetry.noop();
//    }
}
