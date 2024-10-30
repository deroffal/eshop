package fr.deroffal.eshop.observability;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.stereotype.Component;

@Component
public class EshopWebfluxTracerFactory {

    private final OpenTelemetry openTelemetry;

    public EshopWebfluxTracerFactory(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }

    public <T> EshopWebfluxTracer newTracer(Class<T> clazz) {
        return new EshopWebfluxTracer(openTelemetry.getTracer(clazz.getName()));
    }
}
