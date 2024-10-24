package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

import java.util.function.Consumer;
import java.util.function.Function;

import static io.opentelemetry.api.trace.StatusCode.ERROR;

public class EshopTracer {

    private final Tracer tracer;

    public EshopTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    public <T> T executeInSpan(String spanName, Function<Span, T> function) {
        Span span = tracer.spanBuilder(spanName).startSpan();
        try (Scope ignored = span.makeCurrent()) {
            return function.apply(span);
        } catch (Throwable t) {
            span.recordException(t)
                    .setStatus(ERROR);
            throw t;
        } finally {
            span.end();
        }
    }

    public void executeInSpan(String spanName, Consumer<Span> consumer) {
        Span span = tracer.spanBuilder(spanName).startSpan();
        try (Scope ignored = span.makeCurrent()) {
            consumer.accept(span);
        } catch (Throwable t) {
            span.recordException(t)
                    .setStatus(ERROR);
            throw t;
        } finally {
            span.end();
        }
    }
}
