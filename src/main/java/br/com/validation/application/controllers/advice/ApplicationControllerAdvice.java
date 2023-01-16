package br.com.validation.application.controllers.advice;

import br.com.validation.application.exceptions.ValidatorException;
import br.com.validation.application.models.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(ValidatorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(ValidatorException ex) {
        return new ApiErrors(ex.getMessage());
    }
}
