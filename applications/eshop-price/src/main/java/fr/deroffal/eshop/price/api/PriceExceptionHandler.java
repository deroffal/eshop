package fr.deroffal.eshop.price.api;

import fr.deroffal.eshop.price.api.response.ErrorResponse;
import fr.deroffal.eshop.price.domain.exception.CartException;
import fr.deroffal.eshop.price.domain.exception.NotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.Instant;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class PriceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Mono<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return Mono.just(new ErrorResponse(Instant.now(), e.getMessage()));
    }

    @ExceptionHandler(CartException.class)
    @ResponseStatus(BAD_REQUEST)
    public Mono<ErrorResponse> handleCartException(CartException e) {
        return Mono.just(new ErrorResponse(Instant.now(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public Mono<ErrorResponse> handleException(Exception e) {
        return Mono.just(new ErrorResponse(Instant.now(), e.getMessage()));
    }
}
