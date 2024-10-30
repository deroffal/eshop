package fr.deroffal.eshop.observability;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import reactor.core.publisher.Mono;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.opentelemetry.api.trace.StatusCode.ERROR;

public class Tracing {

    private final Tracer tracer;

    public Tracing(Tracer tracer) {
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

    public <T> Mono<T> executeInSpan(String spanName, Consumer<Span> init, Supplier<Mono<T>> toExecute, BiConsumer<Span, T> next) {
        return Mono.just(tracer.spanBuilder(spanName).startSpan())
                .doOnNext(init)
                .flatMap(span -> toExecute.get()
                        .doOnNext(result -> next.accept(span, result))
                        .doOnError(error -> span.recordException(error)
                                .setStatus(StatusCode.ERROR))
                        .doFinally(ignored -> span.end())
                );

    }

}
