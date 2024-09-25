package fr.deroffal.eshop.product.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
class ProductControllerAdvice {

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(new ErrorResponse(exception.getMessage()));
    }

    record ErrorResponse(String description) {
    }
}
