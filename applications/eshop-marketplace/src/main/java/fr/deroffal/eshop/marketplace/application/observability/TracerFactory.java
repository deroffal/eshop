package fr.deroffal.eshop.marketplace.application.observability;

import io.opentelemetry.api.OpenTelemetry;
import org.springframework.stereotype.Component;

@Component
public class TracerFactory {

    private final OpenTelemetry openTelemetry;

    public TracerFactory(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }

    public <T> EshopTracer newTracer(Class<T> clazz) {
        return new EshopTracer(openTelemetry.getTracer(clazz.getName()));
    }
}
