package ru.netology.authorizationService.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.authorizationService.exception.InvalidCredentials;
import ru.netology.authorizationService.exception.NotFoundException;
import ru.netology.authorizationService.exception.UnauthorizedUser;

import java.util.*;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialHandler(InvalidCredentials e) {
        return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        var errorStr = e.getBindingResult().getAllErrors();
        errorStr.forEach((err) -> {
            String errorMess = err.getDefaultMessage();
            String field = ((FieldError) err).getField();
            errors.put(field, errorMess);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().stream()
                .forEach(i -> {
                    String propertyPath = i.getPropertyPath().toString();
                    String property = propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
                    String message = i.getMessage();
                    errors.put(property, message);
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
