package fr.deroffal.eshop.marketplace.api;

import java.time.Instant;

public record ErrorResponse(
        Instant time,
        String description
) {
}
