package com.example.springboot_study.exception;

import lombok.Getter;

@Getter
public enum ResponseCode {

    NOT_FOUND(404, "not_found", " NOT FOUND."),
    INTERNAL_SERVER_ERROR(500, "internal_server_error", "INTERNAL SERVER ERROR.");

    private final String code;
    private final String message;
    private int status;

    ResponseCode(int status, final String code, final String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
