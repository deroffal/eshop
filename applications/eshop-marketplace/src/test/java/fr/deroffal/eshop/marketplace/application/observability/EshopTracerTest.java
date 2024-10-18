package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static fr.deroffal.eshop.marketplace.application.Profiles.Otel.SKIP_OTEL;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * See <a href="https://github.com/open-telemetry/opentelemetry-java/blob/main/sdk/testing/src/test/java/io/opentelemetry/sdk/testing/junit5/OpenTelemetryExtensionTest.java">OpenTelemetryExtensionTest</a>.
 * Here, we can test that span are actually created by OpenTelemetry sdk.
 */
@ActiveProfiles(SKIP_OTEL)
@SpringBootTest(classes = OpenTelemetryTestConfiguration.class)
public class EshopTracerTest {

    @Autowired
    private OpenTelemetryExtension otelTesting;

    private EshopTracer eshopTracer;

    @BeforeEach
    void setUp() {
        OpenTelemetry openTelemetry = otelTesting.getOpenTelemetry();
        TracerFactory tracerFactory = new TracerFactory(openTelemetry);
        eshopTracer = tracerFactory.newTracer(Example.class);
    }


    @Test
    void executeInSpan_function_success() {
        Example example = new Example();

        var result = eshopTracer.executeInSpan("span-name", span -> {
            return example.hello();
        });

        assertThat(result).isEqualTo("hello");

        assertThat(otelTesting.getSpans())
                .singleElement()
                .satisfies(span -> assertThat(span.getName()).isEqualTo("span-name"));
    }


}
