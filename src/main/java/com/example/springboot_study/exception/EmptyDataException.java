package com.example.springboot_study.exception;

public class EmptyDataException extends RuntimeException{

    private ResponseCode responseCode;

    public EmptyDataException() {
        super();
    }

    public EmptyDataException(ResponseCode responseCode) {
        super(responseCode.getMessage());
    }

//    public EmptyDataException(String message) {
//        super(message);
//    }
//
//    public EmptyDataException(String message, Throwable cause) {
//        super(message, cause);
//    }
//
//    public EmptyDataException(Throwable cause) {
//        super(cause);
//    }
}
