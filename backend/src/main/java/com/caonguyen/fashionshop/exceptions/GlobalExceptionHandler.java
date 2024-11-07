package com.caonguyen.fashionshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetails> handleConflictException(ConflictException e) {
        ErrorDetails err = new ErrorDetails(e.getMessage(), null);
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFoundException(NotFoundException e) {
        ErrorDetails err = new ErrorDetails(e.getMessage(), null);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorDetails> handleUnauthorizedException(UnAuthorizedException e) {
        ErrorDetails err = new ErrorDetails(e.getMessage(), null);
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDetails> handleBadRequestException(BadRequestException e) {
        ErrorDetails err = new ErrorDetails(e.getMessage(), null);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
