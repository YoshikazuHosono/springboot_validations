package com.example.demo.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ViolationExceptionHandler extends ResponseEntityExceptionHandler {

    private ObjectError firstErr;

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, ex.getLocalizedMessage(), null, HttpStatus.BAD_REQUEST, request);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
//        return super.handleExceptionInternal(ex, ex.getLocalizedMessage(), null, HttpStatus.BAD_REQUEST, request);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msg;
        ObjectError firstErr = ex.getBindingResult().getAllErrors().get(0);

        msg = firstErr instanceof FieldError
                ? ((FieldError) firstErr).getField() + " : " + firstErr.getDefaultMessage()
                : firstErr.getDefaultMessage()
        ;

        return super.handleExceptionInternal(ex, msg, null, HttpStatus.BAD_REQUEST, request);
    }
}
