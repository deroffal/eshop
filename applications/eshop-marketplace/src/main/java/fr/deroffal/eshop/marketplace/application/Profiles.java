package fr.deroffal.eshop.marketplace.application;

public class Profiles {

    /**
     * OpenTelemetry profile :
     * I want otel to be active by default. I want to skip it just to simplify testing.
     */
    public static final class Otel {
        public static final String SKIP_OTEL = "skip-otel";
        public static final String NOT_SKIP_OTEL = "!" + SKIP_OTEL;
    }

}
