package com.bloomberg.datawarehouse.exception;

import com.bloomberg.datawarehouse.entity.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    public ResponseEntity<ErrorResponse> doResponseError(final String message, final HttpStatus httpStatus) {
        log.error(String.valueOf(message));
        return ResponseEntity.status(httpStatus).body(new ErrorResponse(message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(final Exception exception) {
        return doResponseError(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
