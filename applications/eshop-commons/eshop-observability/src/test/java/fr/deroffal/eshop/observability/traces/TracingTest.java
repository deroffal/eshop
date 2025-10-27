package fr.deroffal.eshop.observability.traces;

import fr.deroffal.eshop.observability.OpenTelemetryTestConfiguration;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.opentelemetry.api.trace.StatusCode.ERROR;
import static io.opentelemetry.api.trace.StatusCode.UNSET;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(classes = OpenTelemetryTestConfiguration.class, properties = "eshop.observability.enabled=true")
public class TracingTest {

    @Autowired
    private OpenTelemetryExtension otelTesting;

    private Tracing tracing;

    @BeforeEach
    void setUp() {
        OpenTelemetry openTelemetry = otelTesting.getOpenTelemetry();
        TracerFactory tracerFactory = new TracerFactory(openTelemetry);
        tracing = tracerFactory.newTracer(Example.class);
    }

    @AfterEach
    void tearDown() {
        otelTesting.clearSpans();
    }


    @Test
    void executeInSpan_function_success() {
        Example example = new Example();

        var result = tracing.executeInSpan("span-name", _ -> {
            return example.hello();
        });

        assertThat(result).isEqualTo("hello");

        assertThat(otelTesting.getSpans())
                .singleElement()
                .satisfies(span -> {
                    assertThat(span.getName()).isEqualTo("span-name");
                    assertThat(span.getStatus().getStatusCode()).isEqualTo(UNSET);
                });
    }

    @Test
    void executeInSpan_function_throwsException() {
        Example example = new Example();

        assertThatThrownBy(() -> tracing.executeInSpan("span-name", _ -> {
            example.throwException();
        })).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("this is an exception !");

        assertThat(otelTesting.getSpans())
                .singleElement()
                .satisfies(span -> {
                    assertThat(span.getName()).isEqualTo("span-name");
                    assertThat(span.getStatus().getStatusCode()).isEqualTo(ERROR);
                });
    }


}
