package fr.deroffal.eshop.observability.traces;

import fr.deroffal.eshop.observability.OpenTelemetryTestConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example of test class, where we are rather testing `executeInSpan` method, ignoring the behavior of otel.
 */
@SpringBootTest(classes = OpenTelemetryTestConfiguration.class, properties = "eshop.observability.enabled=true")
class TracingNoopTest {

    @Autowired
    private TracerFactory tracerFactory;

    private Tracing tracer;

    @BeforeEach
    void test() {
        tracer = tracerFactory.newTracer(Example.class);
    }

    @Test
    void executeInSpan_function_success() {
        Example example = new Example();

        var result = tracer.executeInSpan("span-name", span -> {
            return example.hello();
        });

        assertThat(result).isEqualTo("hello");
    }

    @Test
    void executeInSpan_consumer_success() {
        Example example = new Example();

        tracer.executeInSpan("span-name", span -> {
            example.world("world");
        });

        assertThat(example.getWorld()).isEqualTo("world");
    }

}
