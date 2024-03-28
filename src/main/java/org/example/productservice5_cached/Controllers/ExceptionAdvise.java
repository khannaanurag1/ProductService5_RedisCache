package org.example.productservice5_cached.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvise {
    @ExceptionHandler({NullPointerException.class,IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<>("kuch toh phata hai", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
