package fr.deroffal.eshop.price.clients;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

@Validated
@ConfigurationProperties(prefix = "price.client")
public record ClientConfiguration(@NotNull ProductConfiguration product) {

    public record ProductConfiguration(@NotNull URI url) {
    }


}
