package fr.deroffal.eshop.price.clients;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;


@Validated
@ConfigurationProperties(prefix = "price.client")
public record ClientConfiguration(@NotNull @Valid ProductConfiguration product) {

    public record ProductConfiguration(@NotNull URI url) {
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(WebClientLogger.requestLogger());
                    exchangeFilterFunctions.add(WebClientLogger.responseLogger());
                })
                .build();
    }

    private static class WebClientLogger {

        private static final Logger logger = LoggerFactory.getLogger(WebClientLogger.class);

        public static ExchangeFilterFunction requestLogger() {
            return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {

                logger.debug("sending {} to {}", clientRequest.method().toString().toUpperCase(), clientRequest.url());

                return Mono.just(clientRequest);
            });
        }

        public static ExchangeFilterFunction responseLogger() {
            return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                logger.debug("received {} from {}", clientResponse.statusCode(), clientResponse.request().getURI());
                return Mono.just(clientResponse);
            });
        }
    }

}
