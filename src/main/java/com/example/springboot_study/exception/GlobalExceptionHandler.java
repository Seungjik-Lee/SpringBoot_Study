package com.example.springboot_study.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<CommonResponse> handleRuntimeException(RuntimeException e) {

        log.info("handleRuntimeException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ResponseCode.NOT_FOUND.getCode())
                .message(e.getMessage())
                .status(ResponseCode.NOT_FOUND.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = EmptyDataException.class)
    protected ResponseEntity<CommonResponse> handleDataEmptyException(EmptyDataException e) {

        log.info("handleEmptyDataException", e);

        CommonResponse response = CommonResponse.builder()
                .code(ResponseCode.INTERNAL_SERVER_ERROR.getCode())
                .message(e.getMessage())
                .status(ResponseCode.INTERNAL_SERVER_ERROR.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
