package com.example.sample.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(Exception e) {
        e.printStackTrace();

        log.error(e.getMessage());

        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SalaryUpdateException.class)
    public ResponseEntity<?> salaryUpdateException(Exception e) {
        e.printStackTrace();

        log.error(e.getMessage());

        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(LinkException.class)
    public ResponseEntity<?> linkException(Exception e) {
        e.printStackTrace();

        log.error(e.getMessage());

        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
