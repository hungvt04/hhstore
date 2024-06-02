package com.datn.be.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class HandleException {

    /**
     * Xu ly exception validation request
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors();
        Map<String, String> responseErrors = new HashMap<>();
        for (FieldError error : errors) {
            responseErrors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(responseErrors, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Xy ly exception custom
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<?> handleRestException(RestException exception) {
        return new ResponseEntity<>(exception.getErrors(), HttpStatus.NOT_ACCEPTABLE);
    }

}
