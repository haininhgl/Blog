package com.example.blogger.exception;

import com.example.blogger.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(APIResponse.newFailureResponse("Tham số không hợp lệ"), HttpStatus.OK);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(APIResponse.newFailureResponse(e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    ResponseEntity<?> handleBadRequestException(AuthenticationException e) {
        return new ResponseEntity<>(APIResponse.newFailureResponse(HttpStatus.UNAUTHORIZED.value(), "Tài khoản không hợp lệ"), HttpStatus.OK);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    ResponseEntity<?> handleBadRequestException(ResourceNotFoundException e) {
        return new ResponseEntity<>(APIResponse.newFailureResponse(HttpStatus.NOT_FOUND.value(), "Không tìm thấy tài nguyên"), HttpStatus.OK);
    }
}
