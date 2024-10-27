package fr.deroffal.eshop.price.application.observability;

import fr.deroffal.eshop.observability.EshopTracer;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class EshopWebfluxTracer {

    private final Tracer tracer;

    public EshopWebfluxTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    public <T> Mono<T> executeInSpan(String spanName, Consumer<Span> init, Supplier<Mono<T>> supplier, BiConsumer<Span, T> next) {
        return Mono.just(tracer.spanBuilder(spanName).startSpan())
                .doOnNext(init)
                .flatMap(span -> supplier.get()
                        .doOnNext(result -> next.accept(span, result))
                        .doOnError(error -> span.recordException(error)
                                .setStatus(StatusCode.ERROR))
                        .doFinally(ignored -> span.end())
                );

    }

    public <T> Mono<T> executeInSpan(String spanName, Supplier<Mono<T>> supplier, BiConsumer<Span, T> next) {
        Consumer<Span> noop = span -> {
        };
        return executeInSpan(spanName, noop, supplier, next);
    }

}
