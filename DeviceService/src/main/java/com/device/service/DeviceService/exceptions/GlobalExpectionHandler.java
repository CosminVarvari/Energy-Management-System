package com.device.service.DeviceService.exceptions;

import com.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExpectionHandler {

    @ExceptionHandler(com.user.service.exceptions.ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(com.user.service.exceptions.ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse respone = ApiResponse.builder().message(message).succes(true).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<ApiResponse>(respone, HttpStatus.NOT_FOUND);

    }
}
