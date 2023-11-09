package com.user.service.UserService.exceptions;

import com.user.service.UserService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExpectionHandler {

    @ExceptionHandler(com.user.service.UserService.exceptions.ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(com.user.service.UserService.exceptions.ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse respone = ApiResponse.builder().message(message).succes(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(respone, HttpStatus.NOT_FOUND);

    }
}
