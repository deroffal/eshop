package fr.deroffal.eshop.observability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static fr.deroffal.eshop.observability.Profiles.SKIP_OTEL;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Example of test class, where we are rather testing `executeInSpan` method, ignoring the behavior of otel.
 */
@ActiveProfiles(SKIP_OTEL)
@SpringBootTest(classes = OpenTelemetryTestConfiguration.class)
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
