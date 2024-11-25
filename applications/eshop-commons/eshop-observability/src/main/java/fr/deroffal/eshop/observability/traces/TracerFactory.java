package fr.deroffal.eshop.observability.traces;

import io.opentelemetry.api.OpenTelemetry;

public class TracerFactory {

    private final OpenTelemetry openTelemetry;

    public TracerFactory(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }

    public <T> Tracing newTracer(Class<T> clazz) {
        return new Tracing(openTelemetry.getTracer(clazz.getName()));
    }
}
