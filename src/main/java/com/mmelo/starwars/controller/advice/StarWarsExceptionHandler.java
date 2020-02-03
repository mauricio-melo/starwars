package com.mmelo.starwars.controller.advice;

import com.mmelo.starwars.commons.StarWarsMessage;
import com.mmelo.starwars.exception.ResourceNotFoundException;
import com.mmelo.starwars.exception.SwapiInternalError;
import com.mmelo.starwars.vo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestControllerAdvice
public class StarWarsExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException (final Exception e) {
        return status(INTERNAL_SERVER_ERROR)
                .body(this.constructErrorResponse(INTERNAL_SERVER_ERROR, StarWarsMessage.SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(SwapiInternalError.class)
    public ResponseEntity<ErrorResponse> handleSwapiInternalError(final Exception e) {
        return status(INTERNAL_SERVER_ERROR)
                .body(this.constructErrorResponse(INTERNAL_SERVER_ERROR, StarWarsMessage.SERVER_ERROR_SWAPI.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound (final Exception e) {
        return status(NOT_FOUND)
                .body(this.constructErrorResponse(NOT_FOUND, StarWarsMessage.RESOURCE_NOT_FOUND.getMessage()));
    }

    private ErrorResponse constructErrorResponse (final HttpStatus httpStatus, final String... messages) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .messages(asList(messages))
                .build();
    }
}
