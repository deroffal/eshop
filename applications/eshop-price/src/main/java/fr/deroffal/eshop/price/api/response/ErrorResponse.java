package fr.deroffal.eshop.price.api.response;

import java.time.Instant;

public record ErrorResponse(
        Instant time,
        String description
) {
}
