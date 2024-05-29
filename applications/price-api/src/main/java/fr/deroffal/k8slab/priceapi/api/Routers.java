package fr.deroffal.k8slab.priceapi.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration(proxyBeanMethods = false)
public class Routers {

    @Bean
    public RouterFunction<ServerResponse> cartRoutes(
        final CartHandler cartHandler,
        final PriceHandler priceHandler
    ) {
        //@formatter:off
        return RouterFunctions.route()
            .POST("/cart", accept(APPLICATION_JSON), cartHandler::newCart)
            .GET("/price/{product}", accept(APPLICATION_JSON), priceHandler::getPrice)
            .build();
        //@formatter:on
    }
}
