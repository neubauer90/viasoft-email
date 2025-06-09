package com.viasoft.emailservice.exception;

public record ErrorResponse(String type, String message, long timestamp) {
    public ErrorResponse(String type, String message) {
        this(type, message, System.currentTimeMillis());
    }
}