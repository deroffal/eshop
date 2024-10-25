package fr.deroffal.eshop.observability;

/**
 * OpenTelemetry profile :
 * I want otel to be active by default. I want to skip it just to simplify testing.
 */
public class Profiles {

    public static final String SKIP_OTEL = "skip-otel";
    public static final String NOT_SKIP_OTEL = "!" + SKIP_OTEL;
}
