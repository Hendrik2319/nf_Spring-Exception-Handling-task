package de.neuefische.springexceptionhandlingtask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException ex) {
        System.err.printf("NoSuchElementException: %s%n", ex.getMessage());
        return new ErrorMessage("NoSuchElementException: %s".formatted(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception ex) {
        System.err.printf("%s: %s%n", ex.getClass().getSimpleName(), ex.getMessage());
        return new ErrorMessage("%s: %s".formatted(ex.getClass().getSimpleName(), ex.getMessage()));
    }

}
