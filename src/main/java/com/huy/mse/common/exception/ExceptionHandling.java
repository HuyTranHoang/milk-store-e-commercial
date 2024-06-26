package com.huy.mse.common.exception;

import com.huy.mse.common.HttpResponse;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> NoHandlerFoundException(NoHandlerFoundException e) {
        return createResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> noResultException(NoResultException e) {
        return createResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<HttpResponse> noSuchElementException(NoSuchElementException e) {
        return createResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<HttpResponse> createResponseEntity(HttpStatus status, String message) {
        HttpResponse httpResponse = new HttpResponse(status.value(), status, status.getReasonPhrase(), message);
        return new ResponseEntity<>(httpResponse, status);
    }
}
