package fr.deroffal.eshop.marketplace.api;

import fr.deroffal.eshop.marketplace.domain.model.CartNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class MarketplaceExceptionHandler {

    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleCartNotFoundException(CartNotFoundException e) {
        return new ErrorResponse(Instant.now(), "Cart is not found");
    }
}
