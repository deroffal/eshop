package fr.deroffal.eshop.marketplace.application.observability;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OpenTelemetryTestConfiguration.class)
class EshopTracerTest {

    @Autowired
    private TracerFactory tracerFactory;

    private EshopTracer tracer;

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

    private static class Example {

        private String world;

        public String hello() {
            return "hello";
        }

        public void world(String world) {
            this.world = world;
        }

        public String getWorld() {
            return world;
        }
    }
}
